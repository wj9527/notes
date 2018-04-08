------------------------------------
Generator							|
------------------------------------
	1,简介
	2,next 方法的参数
	3,for...of 循环
	4,Generator.prototype.throw()
	5,Generator.prototype.return()
	6,next()、throw()、return() 的共同点
	7,yield* 表达式
	8,作为对象属性的 Generator 函数
	9,Generator 函数的this
	10,含义
	11,应用

------------------------------------
Generator-简介						|
------------------------------------
	# 跟Python的生成器,迭代器一样的
	# 正确的定义姿势(*号在function 与函数名中间任意地方都可以)
		function * foo(x, y) { ··· }
		function *foo(x, y) { ··· }
		function* foo(x, y) { ··· }
		function*foo(x, y) { ··· }

	# 使用next()函数来操作生成器的结果

		function* foo(){
			yield 5;
			yield 6;
			return 7;
		}
		
		it = foo();
		
		console.log(it.next());	//{value: 5, done: false}
		console.log(it.next());	//{value: 6, done: false}
		console.log(it.next());	//{value: 7, done: true}
		console.log(it.next());	//{value: undefined, done: true}
	
	# 遍历器对象的next方法的运行逻辑如下
		1,遇到yield表达式，就暂停执行后面的操作,并将紧跟在yield后面的那个表达式的值,作为返回的对象的value属性值
		2,下一次调用next方法时,再继续往下执行,直到遇到下一个yield表达式
		3,如果没有再遇到新的yield表达式,就一直运行到函数结束,直到return语句为止,并将return语句后面的表达式的值,作为返回的对象的value属性值
		4,如果该函数没有return语句,则返回的对象的value属性值为undefined

		* 需要注意的是,yield表达式后面的表达式,只有当调用next方法,内部指针指向该语句时才会执行,因此等于为 JavaScript 提供了手动的"惰性求值"(Lazy Evaluation)的语法功能
			function* gen() {
				yield  123 + 456;
			}
			* yield后面的表达式123 + 456,不会立即求值,只会在next方法将指针移到这一句时,才会求值
		
	
	# return 与 yield
		* 生成器里面 return 只能执行一次,便会结束掉,而yield可以执行多次

	# Generator 函数可以不用yield表达式,这时就变成了一个单纯的暂缓执行函数
		function* f() {
			console.log('执行了！')
		}

		var generator = f();		//不会执行函数
		generator.next();			//调用next()方法才会执行函数
	
	# yield表达式只能用在 Generator 函数里面,用在其他地方都会报错
	# yield表达式如果用在另一个表达式之中,必须放在圆括号里面
		function* demo() {
			console.log('Hello' + yield); 		// SyntaxError
			console.log('Hello' + yield 123); 	// SyntaxError
			console.log('Hello' + (yield)); 	// OK
			console.log('Hello' + (yield 123)); // OK
		}

		* yield表达式'用作函数参数'或'放在赋值表达式的右边',可以不加括号
			function* demo() {
				foo(yield 'a', yield 'b');	// OK
				let input = yield;			// OK
			}

	# 与 Iterator 接口的关系
		* 任意一个对象的Symbol.iterator方法,等于'该对象的遍历器生成函数',调用该函数会返回该对象的一个遍历器对象
		  由于 'Generator 函数就是遍历器生成函数',因此可以把 Generator 赋值给对象的Symbol.iterator属性,从而使得该对象具有 Iterator 接口

			let obj = {
				name:'KevinBlandy',
				[Symbol.iterator]:function* (){
					for(let key of Reflect.ownKeys(this)){
						yield {[key]:Reflect.get(this,key)};
					}
				}
			}		 
			
			for(let item of obj){
				console.log(item);
			}
		
		* Generator 函数执行后,返回一个遍历器对象,该对象本身也具有Symbol.iterator属性,执行后返回自身
			function* foo(){
				yield 5;
			}	
			//执行生成器函数,返回一个遍历器对象
			it = foo();
			
			//该对象自己也有Symbol.iterator属性,执行后,返回 this
			console.log(it === it[Symbol.iterator]());		//true

------------------------------------
next 方法参数						|
------------------------------------
	# yield表达式本身没有返回值,或者说总是返回undefined
	# next方法可以带一个参数,该参数就会被当作上一个yield表达式的返回值
	# 跟 Python 生成器的 send() 方法一样
		function* foo(num){
			for(let x = 0 ;x < num ; x++){
				let param = yield x;
				console.log(`本次迭代参数${param}`);
			}
		}
		
		it = foo(3);
		
		console.log(it.next('我是参数1'));	//0			第一次迭代的参数,获取不到
		console.log(it.next('我是参数2'));	//1			本次迭代参数我是参数2
		console.log(it.next('我是参数3'));	//2			本次迭代参数我是参数3
		console.log(it.next('我是参数4'));	//undefined	本次迭代参数我是参数3
	
	# 可以通过这种方式,修改生成器的执行状态

------------------------------------
next 方法参数						|
------------------------------------
	# for...of循环可以自动遍历 Generator 函数时生成的Iterator对象,且此时不再需要调用next方法
		function* foo (num){
			while(num > 0){
				yield num;
				num -= 1;
			}
		}
		for(let item of foo(5)){
			console.log(item);
		}
		//5,4,3,2,1
	
	# 利用 Generator 函数和for...of循环,实现斐波那契数列
		function* fibonacci() {
			let [prev, curr] = [0, 1];
			for (;;) {
				[prev, curr] = [curr, prev + curr];
				yield curr;
			}
		}
		
		for (let n of fibonacci()) {
			if (n > 1000) {
				break;
			}
			console.log(n);
		}
	
	# 利用for...of循环,可以写出遍历任意对象(object)的方法,原生的 JavaScript 对象没有遍历接口,无法使用for...of循环
	  通过 Generator 函数为它加上这个接口,就可以用了
		//让Object的子对象都支持迭代
		Object.prototype[Symbol.iterator] = function* (){
			let keys = Reflect.ownKeys(this);
			for(let key of keys){
				yield [key,Reflect.get(this,key)];
			}
		}
		
		function Obj(){
			this.name = "KevinBlandy";
			this.age = 22;
		}
		
		for(let [key,value] of new Obj()){
			console.log(`${key} = ${value}`);
		}
		//name = KevinBlandy
		//age = 22
	
	# 除了for...of循环以外,扩展运算符(...),解构赋值和Array.from方法内部调用的,都是遍历器接口
		* 这意味着,它们都可以将 Generator 函数返回的 Iterator 对象,作为参数
		function* numbers () {
			yield 1
			yield 2
			return 3
			yield 4
		}
		
		// 扩展运算符
		[...numbers()] // [1, 2]
		
		// Array.from 方法
		Array.from(numbers()) // [1, 2]
		
		// 解构赋值
		let [x, y] = numbers();
		x // 1
		y // 2
		
		// for...of 循环
		for (let n of numbers()) {
			console.log(n)
		}
		// 1
		// 2
	

------------------------------------
Generator.prototype.throw()			|
------------------------------------
	# Generator 函数返回的遍历器对象,都有一个throw方法,可以在函数体外抛出错误,然后在 Generator 函数体内捕获
		var g = function* () {
			try {
				yield;
			} catch (e) {
				console.log('内部捕获', e);
			}
		};
		
		var i = g();
		i.next();
		
		try {
			i.throw('a');
			i.throw('b');
		} catch (e) {
			console.log('外部捕获', e);
		}
		// 内部捕获 a
		// 外部捕获 b

		* i连续抛出两个错误,第一个错误被 Generator 函数体内的catch语句捕获
		* i第二次抛出错误,由于 Generator 函数内部的catch语句已经执行过了,不会再捕捉到这个错误了,所以这个错误就被抛出了 Generator 函数体,被函数体外的catch语句捕获
	
	# throw方法可以接受一个参数,该参数会被catch语句接收,建议抛出Error对象的实例
		var g = function* () {
			try {
				yield;
			} catch (e) {
				console.log(e);
			}
		};
		
		var i = g();
		i.next();
		i.throw(new Error('出错了！'));
		// Error: 出错了！
	
	# 如果 Generator 函数内部没有部署try...catch代码块,那么throw方法抛出的错误,将被外部try...catch代码块捕获
		var g = function* () {
			yield;
		};
		
		var i = g();
		i.next();
		i.throw('异常了');		//Uncaught 异常了
	
	# throw方法被捕获以后,会附带执行下一条yield表达式,也就是说,会附带执行一次next方法
		var gen = function* gen(){
			try {
				yield console.log('a');
			} catch (e) {
				// ...
			}
			yield console.log('b');
			yield console.log('c');
		}
		
		var g = gen();
		g.next() 	// a
		g.throw() 	// b		执行了throw,被迭代器里面的catch了,还是会附带执行下一次 yield
		g.next() 	// c
		
		* 只要 Generator 函数内部部署了try...catch代码块,那么遍历器的throw方法抛出的错误,不影响下一次遍历
		* 'throw命令与throw方法是无关的,两者互不影响'
	
	# Generator 函数体外抛出的错误,可以在函数体内捕获,反过来,Generator 函数体内抛出的错误,也可以被函数体外的catch捕获
		function* foo() {
			var x = yield 3;
			var y = x.toUpperCase();
			yield y;
		}
		var it = foo();
		it.next(); // { value:3, done:false }
		try {
			it.next(42);
		} catch (err) {
			console.log(err);		//TypeError: x.toUpperCase is not a function
		}
	
	# 一旦 Generator 执行过程中抛出错误,且没有被内部捕获,就不会再执行下去了
		* 如果此后还调用next方法,将返回一个value属性等于undefined,done属性等于true的对象,即 JavaScript 引擎认为这个 Generator 已经运行结束了
			function* foo(){
				yield 5;
				throw e;
				yield 6;
			}
			it = foo();
			console.log(it.next());
			try{
				console.log(it.next());
			}catch(e){
				//skip
			}
			console.log(it.next());
			//{value: 5, done: false}
			//{value: undefined, done: true}
	
------------------------------------
Generator.prototype.return()		|
------------------------------------
	# Generator 函数返回的遍历器对象,还有一个return方法,可以返回给定的值,并且终结遍历 Generator 函数
		function* gen() {
			yield 1;
			yield 2;
			yield 3;
		}

		var g = gen();

		g.next()        // { value: 1, done: false }
		g.return('foo') // { value: "foo", done: true }
		g.next()        // { value: undefined, done: true }
	
		* 如果return方法调用时,不提供参数,则返回值的value属性为undefined
	
	# 如果 Generator 函数内部有try...finally代码块,那么return方法会推迟到finally代码块执行完再执行
		function* gen() {
			try{
				yield 1;
				yield 2;
				yield 3;
			}finally{
				console.log('最终执行');
			}
		}

		var g = gen();

		console.log(g.next())      	//{value: 1, done: false}
		//执行return,会先去执行 finally的代码
		console.log(g.return()) 	//最终执行	 { value: undefined, done: true }		
		console.log(g.next())       // {value: undefined, done: true}

------------------------------------
next(),throw(),return() 的共同点	|
------------------------------------
	# next(),throw(),return()这三个方法本质上是同一件事,可以放在一起理解
	# 它们的作用都是让 Generator 函数恢复执行,并且使用不同的语句替换yield表达式

------------------------------------
yield 表达式						|
------------------------------------
	