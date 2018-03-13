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
		