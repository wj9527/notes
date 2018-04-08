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
	