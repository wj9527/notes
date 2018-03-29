----------------------------
Proxy						|
----------------------------
	1,概述
	2,Proxy 实例的方法
	3,Proxy.revocable()
	4,this 问题
	5,实例:Web 服务的客户端


----------------------------
概述						|
----------------------------
	# 跟Java的Proxy一样,AOP编程
	# 创建Proxy实例对象

		new Proxy(target,handler)

		* target,目标对象(被增强对象)
		* handler,代理控制器(一个对象)
	
	# Hello World
		let obj = {name:'Kevin',age:23};
		let proxyObj = new Proxy(obj,{
			//读取属性时拦截
			get:function(target,key,receiver){
				console.log(`获取属性:${key}`);
				return Reflect.get(target, key, receiver);
			},
			//设置属性时拦截
			set:function(target, key, value, receiver){
				console.log(`设置属性:${key},${value}`);
				return Reflect.set(target, key, value, receiver);
			}
		});
		
		proxyObj.name;			//获取属性:name
		proxyObj.age = 25;		//设置属性:age,25

	
	# 如果handler没有设置任何拦截,那就等同于直接通向原对象
		let obj = {name:'Kevin',age:23};
		let proxyObj = new Proxy(obj,{});
		* handler是一个空对象,没有任何拦截效果,访问proxyObj就等同于访问obj
	
--------------------------------
Proxy 支持的拦截操作一览(13 种)	|
--------------------------------
	# 其实就是 hanlder 的属性
	
	get(target, propKey, receiver)
		* 拦截对象属性的读取,比如proxy.foo和proxy['foo']
		* 'get方法的第三个参数receiver,总是为当前的 Proxy 实例'
			const proxy = new Proxy({}, {
				get: function(target, property, receiver) {
					return receiver;
				}
			});
			proxy.getReceiver === proxy // true
		* 如果一个属性不可配置(configurable)和不可写(writable),则该属性不能被代理,通过 Proxy 对象访问该属性会报错
	
	set(target, propKey, value, receiver)
		* 拦截对象属性的设置,比如proxy.foo = v 或 proxy['foo'] = v
		* 如果目标对象自身的某个属性,不可写或不可配置,那么set方法将不起作用
		* 返回布尔值
		
	has(target, propKey)
		* 拦截propKey in proxy的操作
		* 这个方法会生效,典型的操作就是in运算符
		* 原对象不可配置或者禁止扩展,这时has拦截会报错
		* 返回布尔值
		
	deleteProperty(target, propKey)
		* 拦截delete proxy[propKey]的操作
		* 返回一个布尔值
		
	ownKeys(target)
		* 拦截Object.getOwnPropertyNames(proxy),Object.getOwnPropertySymbols(proxy),Object.keys(proxy)
		* 返回一个数组,该方法返回目标对象所有自身的属性的属性名,而Object.keys()的返回结果仅包括目标对象自身的可遍历属性
		
	getOwnPropertyDescriptor(target, propKey)
		* 拦截Object.getOwnPropertyDescriptor(proxy, propKey)
		* 返回属性的描述对象
		
	defineProperty(target, propKey, propDesc)
		* 拦截Object.defineProperty(proxy, propKey, propDesc）,Object.defineProperties(proxy, propDescs)
		* 返回布尔值
				
	preventExtensions(target)
		* 拦截Object.preventExtensions(proxy)
		* 返回布尔值
		
	getPrototypeOf(target)
		* 拦截Object.getPrototypeOf(proxy)
		* 返回一个对象
			
	isExtensible(target)
		* 拦截Object.isExtensible(proxy)
		* 返个布尔值
			
	setPrototypeOf(target, proto)
		* 拦截Object.setPrototypeOf(proxy, proto)
		* 返回布尔值,如果目标对象是函数,那么还有两种额外操作可以拦截
		
	apply(target, object, args)
		* 拦截 Proxy 实例作为函数调用的操作,比如proxy(...args),proxy.call(object, ...args),proxy.apply(...)
		* 三个参数,分别是目标对象,目标对象的上下文对象(this),目标对象的参数数组
		* 直接调用Reflect.apply方法,也会被拦截
		
	construct(target, args)
		* 拦截 Proxy 实例作为构造函数调用的操作,比如new proxy(...args)