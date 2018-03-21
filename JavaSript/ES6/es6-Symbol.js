------------------------
Symbol					|
------------------------
	# ES5 的对象属性名都是字符串,这容易造成属性名的冲突
		* 你使用了一个他人提供的对象,但又想为这个对象添加新的方法(mixin 模式),新方法的名字就有可能与现有方法产生冲突
		* 如有一种机制,保证每个属性的名字都是独一无二的就好了,这样就从根本上防止属性名的冲突
		* 这就是 ES6 引入Symbol的原因
	
	# ES6 引入了一种新的原始数据类型Symbol,表示独一无二的值
		* 它是 JavaScript 语言的第七种数据类型
			undefined,null,Boolean,String,Number,Object
	
	# Symbol 值通过Symbol函数生成,这就是说,对象的属性名现在可以有两种类型
		* 一种是原来就有的字符串
		* 一种就是新增的 Symbol 类型
			* 凡是属性名属于 Symbol 类型,就都是独一无二的,可以保证不会与其他属性名产生冲突
	
			let s = Symbol();
			typeof s // "symbol"
		
			* 上面代码中,变量s就是一个独一无二的值
			* typeof运算符的结果,表明变量s是 Symbol 数据类型
			* 而不是字符串之类的其他类型
	
	# Symbol函数前不能使用new命令,否则会报错
		* 是因为生成的 Symbol 是一个原始类型的值,不是对象
		* 也就是说,由于 Symbol 值不是对象,所以不能添加属性
		* 基本上,它是一种类似于字符串的数据类型
	
	# Symbol函数可以接受一个字符串作为参数
		* 表示对 Symbol 实例的描述
		* 主要是为了在控制台显示,或者转为字符串时,比较容易区分
			let s1 = Symbol('foo');
			let s2 = Symbol('bar');
			
			s1 // Symbol(foo)
			s2 // Symbol(bar)
			
			s1.toString() // "Symbol(foo)"
			s2.toString() // "Symbol(bar)"
			
			* 如果不加参数,它们在控制台的输出都是Symbol(),不利于区分
	
	# 如果 Symbol 的参数是一个对象,就会调用该对象的toString方法,将其转为字符串,然后才生成一个 Symbol 值
		const obj = {
			toString() {
				return 'abc';
			}
		};
		const sym = Symbol(obj);
		
		console.log(sym); // Symbol(abc)
	
	# Symbol函数的参数只是表示对当前 Symbol 值的描述
		* 因此相同参数的Symbol函数的返回值是不相等的
		// 没有参数的情况
		let s1 = Symbol();
		let s2 = Symbol();
		console.log(s1 === s2); // false
		// 有参数的情况
		let s1 = Symbol('foo');
		let s2 = Symbol('foo');
		console.log(s1 === s2); // false
	
	# Symbol 值不能与其他类型的值进行运算,会报错
		let sym = Symbol('My symbol');
		"your symbol is " + sym
		// TypeError: can't convert symbol to string
		`your symbol is ${sym}`
		// TypeError: can't convert symbol to string
		
		* Symbol 值可以显式转为字符串,不能直接转换为数值
			let sym = Symbol('My symbol');
			String(sym) // 'Symbol(My symbol)'
			sym.toString() // 'Symbol(My symbol)'
		
		* 也可以转换为 bool值
			let sym = Symbol();
			Boolean(sym); 	// true
			!sym  			// false
			if (sym) {
				// ...
			}
			Number(sym); 	// TypeError
			sym + 2; 		// TypeError
			
	# 作为属性名的 Symbol
		* 由于每一个 Symbol 值都是不相等的,这意味着 Symbol 值可以作为标识符，用于对象的属性名,就能保证不会出现同名的属性
		* 这对于一个对象由多个模块构成的情况非常有用,能防止某一个键被不小心改写或覆盖
			let mySymbol = Symbol();
			// 第一种写法
			let a = {};
			a[mySymbol] = 'Hello!';
			// 第二种写法
			let a = {
			  [mySymbol]: 'Hello!'
			};
			// 第三种写法
			let a = {};
			Object.defineProperty(a, mySymbol, { value: 'Hello!' });
			// 以上写法都得到同样结果
			a[mySymbol] // "Hello!"

		* Symbol 值作为对象属性名时,不能用点运算符
			const mySymbol = Symbol();
			const a = {};

			a.mySymbol = 'Hello!';
			a[mySymbol] // undefined
			a['mySymbol'] // "Hello!"

			* 点运算符后面总是字符串,所以不会读取mySymbol作为标识名所指代的那个值,导致a的属性名实际上是一个字符串,而不是一个 Symbol 值
			* 同理,在对象的内部,使用 Symbol 值定义属性时,Symbol 值必须放在方括号之中
				let s = Symbol();
				let obj = {
					[s]: function (arg) { ... }
				};
				obj[s](123);
				* 采用增强的对象写法,上面代码的obj对象可以写得更简洁一些
					let obj = {
						[s](arg) { ... }
					};
	
		
		* Symbol 类型还可以用于定义一组常量,保证这组常量的值都是不相等的
			log.levels = {
				DEBUG: Symbol('debug'),
				INFO: Symbol('info'),
				WARN: Symbol('warn')
			};
			log(log.levels.DEBUG, 'debug message');
			log(log.levels.INFO, 'info message');
			--------------------------------------
			const COLOR_RED    = Symbol();
			const COLOR_GREEN  = Symbol();
			
			function getComplement(color) {
				switch (color) {
					case COLOR_RED:
						return COLOR_GREEN;
					case COLOR_GREEN:
						return COLOR_RED;
					default:
						throw new Error('Undefined color');
				}
			}
		
			* 常量使用 Symbol 值最大的好处,就是其他任何值都不可能有相同的值了,因此可以保证上面的switch语句会按设计的方式工作
		
		* 还有一点需要注意,Symbol 值作为属性名时,该属性还是公开属性,不是私有属性
		