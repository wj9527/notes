----------------------
函数				  |
----------------------
	# 基本的函数定义
		fun max(a:Int, b:Int): Int{
			return if (a > b) a else b;
		}

		fund [函数名]([变量名]:[类型]):[返回值类型]{
			// 函数体
		}



	
	# 表达式函数体
		* 在返回值类型后添加 = ,紧跟函数体
			fun min(a:Int, b:Int):Int = if (a > b) a else b;

		* 甚至可以省略返回值表达式
			fun min(a:Int, b:Int) = if(a > b) a else b;

		* 编译会推导出返回值的类型,所以,这种表达式函数不需要声明返回值类型,以及 return 语句
		* 而非表达式函数,如果存在返回值,必须要声明返回值类型,以及 return 语句
	
