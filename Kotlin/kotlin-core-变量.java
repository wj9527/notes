----------------------
变量				  |
----------------------
	# 变量的声明关键字:var
		var name = "KevinBlandy";

		* 自动的推导出数据类型,不需要手动声明数据类型
		* 可以修改变量的值,但是不能修改变量的数据类型,如果尝试重新赋一个不同数据类型的值,则异常
			
	
	# 手动的声明数据类型
		var name: String = "KevinBlandy";
		var age: Int = 25;
	
		* 如果变量只是声明,并没有初始化,那么必须要声明类型
			var name:String ;
			name = "KevinBlandy" ;
		
		* 变量必须要初始化后才能使用,不然编译异常
	
	# 常量的声明关键字: val
		* 跟 java 的 final 一样,初始化后就不能修改,允许先声明,再初始化

			val name:String;
			name = "KevinBlandy";
		
		* 该变量的引用值不能修改,但是引用的对象,是可以任意修改自身属性值的
	



