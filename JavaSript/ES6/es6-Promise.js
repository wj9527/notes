----------------------------
Promise						|
----------------------------
	1,Promise 的含义
	2,基本用法
	3,Promise.prototype.then()
	4,Promise.prototype.catch()
	5,Promise.prototype.finally()
	6,Promise.all()
	7,Promise.race()
	8,Promise.resolve()
	9,Promise.reject()
	10,应用
	11,Promise.try()

----------------------------
Promise 的含义				|
----------------------------
	# Promise 是异步编程的一种解决方案,比传统的解决方案——回调函数和事件——更合理和更强大
	# 它由社区最早提出和实现,ES6 将其写进了语言标准,统一了用法,原生提供了Promise对象
	# 所谓Promise,简单说就是一个容器,里面保存着某个未来才会结束的事件(通常是一个异步操作)的结果
	# 从语法上说,Promise 是一个对象,从它可以获取异步操作的消息
	# Promise 提供统一的 API,各种异步操作都可以用同样的方法进行处理
	# 'Promise 新建后立即执行'
	# 三种状态
		pending(进行中)
		fulfilled(已成功)
		rejected(已失败)

----------------------------
Promise 入门				|
----------------------------
	let promise = new Promise(function(resolve,reject){
		window.setTimeout(function(){
			resolve('Hello World');
		},3000);
		//reject方法的作用,等同于抛出错误
	});
	
	promise.then(value => {
		//回调处理 3s后输出:Hello World
		console.log(value);
	},error => {
		//异常处理 
		console.log(error);
	});
	
	console.log('js代码执行完毕');

----------------------------
Promise.prototype.then()	|
----------------------------
	# Promise 实例具有then方法,也就是说,then方法是定义在原型对象Promise.prototype上的
	# then方法的第一个参数是resolved状态的回调函数,第二个参数(可选)是rejected状态的回调函数
	# 'then方法返回的是一个新的Promise实例(注意,不是原来那个Promise实例)'	Promise {<pending>}

----------------------------
Promise.prototype.catch		|
----------------------------
	# Promise.prototype.catch方法是.then(null, rejection)的别名,用于指定发生错误时的回调函数
	# then方法指定的回调函数,如果运行中抛出错误,也会被catch方法捕获
		let promise = new Promise(function(resolve,reject){
			resolve('Hello');
		});
		
		promise.then(function(r){
			throw 'then 抛出的异常';
		}).catch(function(e){
			console.log('捕获到了异常:' + e)
		});	
		//捕获到了异常:then 抛出的异常
	
	# 如果 Promise 状态已经变成resolved,再抛出错误是无效的
		const promise = new Promise(function(resolve, reject) {
			resolve('ok');
			throw new Error('test');
		});
		promise
		  .then(function(value) { console.log(value) })
		  .catch(function(error) { console.log(error) });
		// ok
	
	# Promise 对象的错误具有"冒泡"性质,会一直向后传递,直到被捕获为止
		* 也就是说,错误总是会被下一个catch语句捕获
		getJSON('/post/1.json').then(function(post) {
			return getJSON(post.commentURL);
		}).then(function(comments) {
			// some code
		}).catch(function(error) {
			// 处理前面三个Promise产生的错误
		});
	
	# 一般来说,不要在then方法里面定义 Reject 状态的回调函数(即then的第二个参数)总是使用catch方法
		* 理由是第二种写法可以捕获前面then方法执行中的错误,也更接近同步的写法(try/catch)
		* 因此,建议总是使用catch方法,而不使用then方法的第二个参数
	
	# 跟传统的try/catch代码块不同的是,如果没有使用catch方法指定错误处理的回调函数,Promise 对象抛出的错误不会传递到外层代码,即不会有任何反应
		* romise 内部的错误不会影响到 Promise 外部的代码,通俗的说法就是"Promise 会吃掉错误"
	
	# Node 有一个unhandledRejection事件,专门监听未捕获的reject错误
		process.on('unhandledRejection', function (err, p) {
			throw err;
		});

	# 一般总是建议,Promise 对象后面要跟catch方法,这样可以处理 Promise 内部发生的错误
		* catch方法返回的还是一个 Promise 对象,因此后面还可以接着调用then方法
		const someAsyncThing = function() {
		const someAsyncThing = function() {
			return new Promise(function(resolve, reject) {
				// 下面一行会报错，因为x没有声明
				resolve(x + 2);
			});
		};

		someAsyncThing().catch(function(error) {
			console.log('oh no', error);
		}).then(function() {
			console.log('carry on');
		});
		// oh no [ReferenceError: x is not defined]
		// carry on

		* 代码运行完catch方法指定的回调函数,会接着运行后面那个then方法指定的回调函数,如果没有报错,则会跳过catch方法
		
	# catch方法之中,还能再抛出错误
		someAsyncThing().then(function() {
			return someOtherAsyncThing();
		}).catch(function(error) {
			console.log('oh no', error);
			y + 2;	// 下面一行会报错，因为 y 没有声明
		}).then(function() {
			console.log('carry on');			//不会输出,因为上层catch抛出了异常
		});
		// oh no [ReferenceError: x is not defined]

		* catch方法抛出一个错误,因为后面没有别的catch方法了,导致这个错误不会被捕获,也不会传递到外层
		* 改写一下,结果就不一样了
		
		const someAsyncThing = function() {
			return new Promise(function(resolve, reject) {
				// 下面一行会报错，因为x没有声明
				resolve(x + 2);
			});
		};

		someAsyncThing().then(function() {
			return someOtherAsyncThing();
		}).catch(function(error) {
			console.log('oh no', error);
			// 下面一行会报错，因为y没有声明
			y + 2;
		}).catch(function(error) {
			//捕获了上层catch抛出的异常
			console.log('carry on', error);
		});
		// oh no [ReferenceError: x is not defined]
		// carry on [ReferenceError: y is not defined]
			
				
----------------------------
Promise.prototype.finally()	|
----------------------------
	# finally方法用于指定不管 Promise 对象最后状态如何,都会执行的操作
		* 该方法是 ES2018 引入标准的

	# finally方法的回调函数不接受任何参数,这意味着没有办法知道,前面的 Promise 状态到底是fulfilled还是rejected
		* 这表明,finally方法里面的操作,应该是与状态无关的,不依赖于 Promise 的执行结果
	
	# finally本质上是then方法的特例
		promise.finally(() => {
		  // 总是会执行的语句
		});

		// 等同于
		promise.then(
			result => {
			  // 总是会执行的语句
			  return result;
			},
			error => {
			  // 总是会执行的语句
			  throw error;
			}
		);


	
	# 它的实现也很简单
		Promise.prototype.finally = function (callback) {
			let P = this.constructor;
			return this.then(
				value  => P.resolve(callback()).then(() => value),
				reason => P.resolve(callback()).then(() => { throw reason })
			);
		};

		* 不管前面的 Promise 是fulfilled还是rejected,都会执行回调函数callback
		* finally方法总是会返回原来的值
			// resolve 的值是 undefined
			Promise.resolve(2).then(() => {}, () => {})

			// resolve 的值是 2
			Promise.resolve(2).finally(() => {})

			// reject 的值是 undefined
			Promise.reject(3).then(() => {}, () => {})

			// reject 的值是 3
			Promise.reject(3).finally(() => {})






----------------------------
Promise.all					|
----------------------------
	# Promise.all方法用于将多个 Promise 实例,包装成一个新的 Promise 实例
		* 所有实例都完成,才完成,最终结果以[]形式传递给形参
		* 任何实例异常,都会触发 Promise.all 实例的 rejected,此时第一个被reject的实例的返回值,会传递给Promise.all的回调函数

		// 生成一个Promise对象的数组
		const promises = [2, 3, 5, 7, 11, 13].map(function (id) {
			return getJSON('/post/' + id + ".json");
		});
		
		Promise.all(promises).then(function (posts) {
			// ...
		}).catch(function(reason){
			// ...
		});

		const p = Promise.all([p1, p2, p3]);

		* Promise.all方法的参数可以不是数组,但必须具有 Iterator 接口,且返回的每个成员都是 Promise 实例
		* 只有p1,p2,p3的状态都变成 fulfilled,p的状态才会变成fulfilled,此时p1,p2,p3的返回值组成一个数组,传递给p的回调函数
		* 只要p1,p2,p3之中有一个被 rejected,p的状态就变成rejected,此时第一个被reject的实例的返回值,会传递给p的回调函数
	
	# 如果作为参数的 Promise 实例,自己定义了catch方法,那么它一旦被rejected,并不会触发Promise.all()的catch方法

----------------------------
Promise.race				|
----------------------------
	# Promise.race方法同样是将多个 Promise 实例,包装成一个新的 Promise 实例
		* 任何实例先完成,就会回调 Promise.race 实例的 then
		* 任何实例异常,都会触发 Promise.all 实例的 rejected,此时第一个被reject的实例的返回值,会传递给Promise.all的回调函数

		const p = Promise.race([p1, p2, p3]);

		* 只要p1,p2,p3之中有一个实例率先改变状态,p的状态就跟着改变
		* 哪个率先改变的 Promise 实例的返回值,就传递给p的回调函数
		* Promise.race方法的参数与Promise.all方法一样,如果不是 Promise 实例,就会先调用下面讲到的Promise.resolve方法,将参数转为 Promise 实例再进一步处理
	
	# 一个小Demo
		const p = Promise.race([
			fetch('/resource-that-may-take-a-while'),
			new Promise(function (resolve, reject) {
				setTimeout(() => reject(new Error('request timeout')), 5000)
			})
		]);
		
		p.then(console.log).catch(console.error);
		
		* 如果 5 秒之内fetch方法无法返回结果,变量p的状态就会变为rejected,从而触发catch方法指定的回调函数

----------------------------
Promise.resolve()			|
----------------------------
	# 有时需要将现有对象转为 Promise 对象,Promise.resolve方法就起到这个作用

		const jsPromise = Promise.resolve($.ajax('/user.json'));
		* 上面代码将 jQuery 生成的deferred对象,转为一个新的 Promise 对象
	
	# Promise.resolve等价于下面的写法
		let promise = Promise.resolve('foo')
		// 等价于
		let promise = new Promise(function(resolve){
			resolve("foo");
		});
	
	# Promise.resolve方法的参数分成四种情况
		1,参数是一个 Promise 实例
			* 参数是 Promise 实例,那么Promise.resolve将不做任何修改,原封不动地返回这个实例

		2,参数是一个thenable对象
			* thenable对象指的是具有then方法的对象,比如下面这个对象	
			* Promise.resolve方法会将这个对象转为 Promise 对象,然后就立即执行thenable对象的then方法
				let thenable = {
					then: function(resolve, reject) {
						resolve(42);
					}
				};

				let p1 = Promise.resolve(thenable);
				p1.then(function(value) {
					console.log(value);  // 42
				});
				* thenable 对象的 then 方法执行后,对象p1的状态就变为 resolved, 从而立即执行最后那个 then 方法指定的回调函数,输出 42

		3,参数不是具有then方法的对象,或根本就不是对象
			* 如果参数是一个原始值,或者是一个不具有then方法的对象,则Promise.resolve方法返回一个新的 Promise 对象,状态为resolved
				const p = Promise.resolve('Hello');
				p.then(function (s){
					console.log(s)
				});
				//Hello

				* 上面代码生成一个新的 Promise 对象的实例p
					* 由于字符串Hello不属于异步操作(判断方法是字符串对象不具有 then 方法),返回 Promise 实例的状态从一生成就是resolved,所以回调函数会立即执行
					* Promise.resolve方法的参数,会同时传给回调函数

		4,不带有任何参数
			//TODO 




