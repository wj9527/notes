--------------------------------
创建函数						|
--------------------------------
	# 创建函数
		function name {
			commands
		}

		name(){
			commands
		}
	
		* name ,函数的唯一名称,不唯一的话,会被覆盖
		* commands ,代码
	
	# 调用,直接写函数名称就可以
		function say { 
			echo "Hello"
		}
		say
	
		* 函数必须先定义再执行
	
--------------------------------
返回值							|
--------------------------------
	# 默认退出状态码
		* 默认情况下,函数的退出状态码是函数中最后一条命令返回的退出状态码
		* 在函数执行结束后,可以用标准变量$?来确定函数的退出状态码
			function say { 
				echo "Hello"
			}
			say
			echo "status = $?"
	
	
	# 使用 return
		* return命令来退出函数并返回特定的退出状态码
		* return命令允许指定一个整数值来定义函数的退出状态码
		* 退出状态码必须是0~255,并且遇到return ,函数终止执行
			retun val
	
	# 使用函数输出
		* 对函数的输出进行保存
		* 跟保存命令的输出一样

			function say {
				echo "Hello"
			}
			result=`say`	# result=$(say)
			echo $result
		
		* 通过这种技术,还可以返回浮点值和字符串值
		* 这使它成为一种获取函数返回值的强大方法

--------------------------------
函数中使用变量					|
--------------------------------
	# 函数的参数
		* bash shell会将函数当作小型脚本来对待
		*  意味着可以像普通脚本那样向函数传递参数
			function foo {
				for (( i = 0 ; i < $# ; i++ )) ; do
					echo "param[${i}] = ${!i}"	
				done
			}
			foo $JAVA_HOME "参数1" "参数3"
	
	# 处理变量
		* 函数使用两种类型的变量
			全局变量
			局部变量
		
		* 全局变量	
			* shell脚本中任何地方都有效的变量
			* 你在脚本的主体部分定义了一个全局变量,那么可以在函数内读取它的值
			* 如果你在函数内定义了一个全局变量,可以在脚本的主体部分读取它的值
			* 默认情况下,你在脚本中定义的任何变量都是全局变量,在函数外定义的变量可在函数内正常访问
				function foo {
					globalVal="Hello"
				}
				foo	# 函数要执行,全局变量才会被载入内存
				echo $globalVal
		
		*  局部变量
			* 在变量赋值语句中使用local关键字
			* local关键字保证了变量只局限在该函数中
				local localVal="Hello"
			
--------------------------------
数组变量和函数					|
--------------------------------
	# 在函数中使用数组变量值有点麻烦,而且还需要一些特殊考虑
	# 向函数传入数组参数
		* 必须将该数组变量的值分解成单个的值,然后将这些值作为函数参数使用
		*  在函数内部,可以将所有的参数重新组合成一个新的变量

			function testit {
				local newarray
				# 获取所有的参数,其实就是数组
				newarray=`echo "$@"`
				echo "函数内部收到的数组: ${newarray[*]}"
			}

			myarray=(1 2 3 4 5)

			echo "外面定义的数组 ${myarray[*]}"
			
			# 把数组一起传递进去
			testit ${myarray[*]}

		* 求和demo
			#!/bin/bash
			function addarray {
				local sum=0
				local newarray
				newarray=`echo "$@"`
				for value in ${newarray[*]} ; do
					sum=$[ $sum + $value ]
				done
				echo $sum
			}
			myarray=(1 2 3 4 5)
			echo "原始数组: ${myarray[*]}"
			arg1=`echo ${myarray[*]}`
			result=`addarray $arg1`
			echo "计算结果是: $result"
	
	# 从函数返回数组
		* 从函数里向shell脚本传回数组变量也用类似的方法
		*  函数用echo语句来按正确顺序输出单个数组值,然后脚本再将它们重新放进一个新的数组变量中

		* 把每个数组元素都 * 2 后返回demo
			function arraydblr {
				local array
				local result
				local index=0
				array=`echo "$@"`
				for val in $array ; do
					result[${index}]=$[$val * 2]
					index=$[${index} + 1]
				done 
				echo "${result[*]}"
			}
			array=(1 2 3 4 5 6)
			echo "原始数组:${array[*]}" 
			result=`arraydblr ${array[*]}`
			echo "函数返回数组:${result}"


--------------------------------
函数的递归						|
--------------------------------
		

