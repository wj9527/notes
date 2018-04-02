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
		
















