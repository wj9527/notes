-------------------------
目录与包结构			 |
-------------------------
	# 包的声明,跟java一样,不多说

	# 导入类库
		* Kotlin 对变量的啥的管理单位是 package,也就是说 kt 文件的名称是啥,显得不那么重要
		* Kotlin一个 kt 文件里面可能定义了N多的类,函数,需要使用什么,就导入什么

			package io.kevinblandy.funcs
			fun max(a:Int, b:Int) = if (a > b) a else b;

			import io.kevinblandy.funcs.max
			fun main(args:Array<String>){
				println(max(1,2));
			}

		*  相同 package 下的所有 kt 文件中,不能重复定义相同名称的变量,否则异常

		* 可以使用: * 来导入所有

		* 当导入的变量与当前包环境的变量名称冲突的时候

			* 如果是使用 * 导入的变量冲突,则当前包的变量优先级大,反之,则 import 的变量优先级大

				import io.kevinblandy.funcs.x;
				import io.kevinblandy.funcs.*;
				var x = "inner";
				var y = "inner";
				fun main(args:Array<String>){
					print("x=$x, y=$y")		// x=outer, y=inner
				}
			
		