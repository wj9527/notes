-------------------
let					|
-------------------
	# 该关键字声明的变量,仅仅在当前代码块有效
		//代码
		{
			var y = 5;
			let x = 5;
		}
		console.log(y);
		console.log(x);
		//执行结果
		5											//变量y可以被访问
		Uncaught ReferenceError: x is not defined	//变量x未定义

		* for循环中的变量,就非常适合使用 let,当前的i只在本轮循环有效,所以每一次循环的i其实都是一个新的变量
		* 每一轮循环的变量i都是重新声明的,那它怎么知道上一轮循环的值,从而计算出本轮循环的值
		  这是因为 JavaScript 引擎内部会记住上一轮循环的值,初始化本轮的变量i时,就在上一轮循环的基础上进行计算
	
	# for循环中的作用域
		for(let x = 0 ;x < 10 ;x ++){
			let x = 'KevinBlandy';
			console.log(x);
		}
		* 执行结果是打印了10次 'KevinBlandy'
		* 说明 for 循环中的 x 与 循环体中声明的 x 在不同的作用域
	
	# 不会存在变量提升
		* 在变量声明之前,使用 var 声明的变量,它的值为 undefined
			console.log(x);		//undefined
			var x = 5;
		
		* let 声明的变量,必须要先声明在使用
			console.log(x);		
			let x = 5;

			Uncaught ReferenceError: x is not defined
	
	# 暂时性死区
		* 只要块级作用域内存在let命令,它所声明的变量就"绑定"这个区域,不再受外部的影响
			var temp = 5;
			{
				temp = 6;           //Uncaught ReferenceError: temp is not defined
				let temp;
			}

			* 存在全局变量temp,但是块级作用域内let又声明了一个局部变量temp,导致后者绑定这个块级作用域
			* 所以在let声明变量前,对temp赋值会报错
	
		* ES6 明确规定,如果区块中存在let和const命令,这个区块对这些命令声明的变量,从一开始就形成了封闭作用域,'凡是在声明之前就使用这些变量,就会报错'
		* 总之,在代码块内,使用let命令声明变量之前,该变量都是不可用的.
		* 这在语法上,称为"暂时性死区"(temporal dead zone,简称 TDZ)

		* "暂时性死区"也意味着typeof不再是一个百分之百安全的操作
			typeof x                    //Uncaught ReferenceError: x is not defined
			let x = 5;
			console.log(typeof xxx)       //undefined

			* 因为 x 被let声明,所以之前都是属于 'x' 的死区,在声明之前使用都会抛出异常
			* xxx 是一个根本没有被声明过的变量,使用 typeof ,结果返回,undefined
			* '在没有let之前,typeof运算符是百分之百安全的,永远不会报错,现在这一点不成立了'
		
		* 一些不容易被发现的'死区'
			------------------------------------------------
			function bar(x = y, y = 2) {
				return [x, y];
			}
			bar(); // 报错Uncaught ReferenceError: y is not defined

			* x = y的时候,y还没有被声明,y属于死区
			* 如果 (x = 2,y = x),就ok,因为x已经被声明,可以被赋值给后面的y
			------------------------------------------------
			var x = x;		//ok
			let x = x;		// ReferenceError: x is not defined

			* let 与 var不同,let声明会报错,也是因为死区的问题,x等于的那个x还没有被声明,就用来赋值
		
		* 总之,暂时性死区的本质就是,只要一进入当前作用域,所要使用的变量就已经存在了,但是不可获取
		* 只有等到声明变量的那一行代码出现,才可以获取和使用该变量.(先声明,后使用)
	
	# 不允许重复声明
		* let不允许在相同作用域内,重复声明同一个变量
			{
				let x = 4;
				let x = 5;      //Uncaught SyntaxError: Identifier 'x' has already been declared
			}
		* 那么在方法中,也就不允许对形参进行 let 声明
			function test(x) {
				let x = 4;
			}
			test(5);     //Uncaught SyntaxError: Identifier 'x' has already been declared
	
-------------------
块级作用域			|
-------------------
	# 为啥要有块级作用域
		* ES5 只有全局作用域和函数作用域,没有块级作用域,这带来很多不合理的场景
			var tmp = new Date();
			function f() {
				//在变量声明之前使用,该值为 undefined
				console.log(tmp);
				if (false) {
					//函数块里面声明了变量
					var tmp = 'hello world';
				}
			}
			f(); // undefined

			* if代码块的外部使用外层的tmp变量,内部使用内层的tmp变量(变量提升)
			* 但是,函数f执行后,输出结果为undefined,原因在于变量提升,导致内层的tmp变量覆盖了外层的tmp变量
			------------------------------------------------
			var s = 'hello';
			for (var i = 0; i < s.length; i++) {
				console.log(s[i]);
			}
			console.log(i); // 5
			* 变量i只用来控制循环,但是循环结束后,它并没有消失,泄露成了全局变量
	
	# 

			
		
	
