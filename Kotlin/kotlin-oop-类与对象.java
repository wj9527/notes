------------------------
类						|
------------------------
	# 简单的类的定义
		class User(
			val name:String,
			val age:Int
		)

		* 语法: class [类名称] (val [属性名]:[属性类型]);

		* 属性的访问权限默认为: public
	
	
	# 定义 getter/setter
		* 跟 js 一个德行,getter 的值是计算得来的
			class User {
				var name : String
					get() {
						return "这个是读取的name属性"
					}
					set(value: String ){
						println("这个是设置的name属性:$name")
					}
			}
			println(user.name);
		
		* 语法: 
			val [属性名称] : [数据类型] get(){ 
				[计算代码]
				return [计算后的返回值] 
			}
		
		* 如果仅仅定义 getter, 那么属性的声明必须是: val
		* 如果需要定义 setter, 那么属性的声明必须是: var 
		* 也可以省略 {} ,使用简单的表达式
			val old : Boolean get() = this.age > 25;

			val old get() = this.age > 25;

			* 同理,甚至可以省略数据类型,编译会根据表达式的返回值自动推导出数据类型
	

------------------------
对象					|
------------------------
	