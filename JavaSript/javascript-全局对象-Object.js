----------------------------
Object						|
----------------------------
	# 所有对象都是属于Object
		* 也就是说,它的实例方法,是所有对象都具备的
	
	# 实例属性
		constructor
			* 返回当前对象的构造函数
			* 就是创建了当前这个对象的函数
		
	# 实例方法
		hasOwnProperty();
			* 当前对象是否具备指定名称的属性/方法
			* 并不能会去检测原型对象
			* 说白了,就是指定属性是不是我自己的属性
			* 返回 boolean 
		
		isPrototypeOf();
			* 判断当前对象,是否在指定对象的原型链中,用于检测原型,和 instanceof 一样的工作原理
			* 参数对象,是否在 this(原型对象) 的原型链中
			* 返回 boolean
		
		propertyIsEnumerable();
			* 判断当前对象指定名称的属性,是否可以被 for int 枚举
			* 仅仅检索当前对象的属性,不会去原型中找属性
			* 返回 boolean
	
		toLocaleString();
			* 以字符串的形式返回当前对象,字符串格式跟当前执行环境的地区对应
		
		toString();
			* 以字符串的形式返回当前对象
		
		valueOf();
			* 返回对象的字符串,

	# 静态方法
		create();
			* 直接创建出一个对象
			* 第一个参数就是设置给该对象的原型对象
			* 第二个参数,用于对对象属性进行更详细的描述(可选)
				writable		:是否可任意写
				configurable	:是否能够删除，是否能够被修改
				enumerable		:是否能用 for in 枚举
				value			:值,'当设置后不能设置get和set'	
				get				:访问属性的时候触发,记住千万不能使用 this.当前属性名称
				set				:设置属性的时候触发,记住千万不能使用 this.当前属性名称
			* var o = Object.create({x:1,y:2});		//o 继承了属性,x和y
			* var o = Object.create(obj,{
									z:{		//z属性控制
										value:'yupeng',		//值
										writable:true		//可写
									},
									k: {	//k属性控制
										configurable: false,				//不可配置
										get: function() { return bar; },	//获取
										set: function(value) { bar=value }	//设置
										}
								})

		getPrototypeOf(obj);
			* 获取指定对象原型对象
		
		keys(obj);
			* 获取指定对象的所有属性
			* 可枚举,自身的属性
			* 该属性不包含原型对象的属性以及不可枚举属性
			* 返回数组
		
		getOwnPropertyNames(obj);
			* 返回对象的所有属性,不管是可枚举的还是不可枚举的,是一个数组
			* 自身的属性就OK,不论是否可以枚举
			* 返回的属性不包含原型对象的属性
		
		defineProperty(); 
			* 给指定的对象设置属性
			* 三个参数
				1,要设置属性的对象
				2,设置什么属性(以字符串表示属性的名称)
				3,Options配置项({})
						configurable	属性能否被删除或者重新定义	
						enumerable		遍历对象的时候属性是否可见	
						value			属性值，'当设置后不能设置get和set'	
						writable		属性能否改变	
						get				当获取属性的时候触发,记住千万不能使用 this.当前属性名称	
						set				当设置属性的时候触发,记住千万不能使用 this.当前属性名称
		
		defineProperties();
			* 跟上面一样,只有两个参数
			* 对对象进行批量的属性添加,覆盖
			* 第一个参数还是对象,第二个参数,里面是一堆对象,key就是属性名,value又是一个对象,就是属性的配置
				Object.defineProperties(user,{
					x:{
						value:3
					},
					y:{
						value:2
					}
				});
			
		getOwnPropertyDescriptor(obj,prop);		
			* 获取指定对象,指定属性的...属性
			* 就是参数1这个对象的参数2的属性的一些属性(只读...等等)
			* 不能检测从原型继承的属性
			* Object.getOwnPropertyDescriptor(user,"name")
		

				


		
		
	
