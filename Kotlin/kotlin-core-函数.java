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
	
	
	# 函数的调用,可以使用命名参数
		fun foo(var1:String, var2:String, var3:String ){
			println("var1=$var1, var2=$var2, var3=$var3")
		}

		fun main(args:Array<String>) {
			foo(var3="1", var2="2", var1="3")	// var1=3, var2=2, var3=1
		}

		* 因为调用的时候使用命名参数,那么传递参数的时候,可以不按照顺序传递
		* 一个参数使用命名参数,必须的参数都使用命名参数
	
		* 调用 java 的方法,不能使用命名参数(java8以后才出现了参数名称保留机制,Kotlin为了兼容jdk6)
	
	# 参数可以有默认值
		* 在类型后面使用 = 定义默认值
			fun foo(var1:String, var2:String = "default" ){
				println("var1=$var1, var2=$var2")
			}
		
		* 使用常规的调用语法时, 必须按照函数声明中定义的参数顺序来给定参数, 可以省略的只有排在末尾的参数
		* 如果使用命名参数, 可以省略中间的一些参数, 也可以以想要的任意顺序只给定你需要的参数 
		* 参数的默认值是被编码到被调用的函数中,而不是调用的地方 

		* 从 Java 中调用 Kotlin 函数的时候,必须显式地指定所有参数值
		* 如果需要从 Java 代码中做频繁的调用,而且希望它能对 Java 的调用者更简便,可以用 @JvmOverloads 注解它
		* 这个注解指示编译器生成 Java 重载函数, 从最后一个开始省略每个参数
			fun foo(var1:String, var2:String,var3:String){
			}
			fun foo(var1:String, var2:String){
			}
			fun foo(var1:String){
			}
	

		


			

	


