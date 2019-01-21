-----------------
数据类型字符串	 |
-----------------
	# 字符串是shell编程中最常用最有用的数据类型(除了数字和字符串,也没啥其它类型好用了)
	# 字符串可以用单引号,也可以用双引号,也可以不用引号,单双引号的区别跟PHP类似

	# 单引号
		str='this is a string'

		* 单引号里的任何字符都会原样输出,单引号字符串中的变量是无效的
			name="KevinBlandy"
			echo 'Hello ${name}'	-> Hello ${name}
			
		* 单引号字串中不能出现单独一个的单引号(对单引号使用转义符后也不行)
				str='hello, \'' -> unexpected EOF while looking for matching `''

		* 单引号可成对出现,作为字符串拼接使用
			name='v'
			str='hello, '$name' !'	-> hello, v !
	
	# 双引号
		your_name='vin'
		str="Hello, I know you are \"$your_name\"! \n"
		echo -e $str
	
	# 字符串的拼接
		name="vin"

		greeting="hello, "$name" !"		-> hello, vin !
		greeting_1="hello, ${name} !"	-> hello, vin !

		greeting_2='hello, '$name' !'	-> hello, vin !
		greeting_3='hello, ${name} !'	-> hello, ${name} !
	
	#  获取字符串的长度
		#!/bin/bash
		name="KevinBlandy"
		echo ${#name}			-> 11

		S="KevinBlandy"
		echo "${S} size = ${#S}"	-> 11
	
	# 提取子字符串
		string="KevinBlandy"
		echo ${string:1:4} -> evin

		* ${:x:y} ,x表示开始的角标,y表示取多少个字符
	
	# 查找子字符串角标
		#!/bin/bash
		NAME="KevinBlandy"
		S="B"
		index=`expr index ${NAME} ${S}`
		echo "${index}"	# -> 6

		* 返回的开始位置是从1开始计算的
		* 如果找不到返回0
		* 实际上使用的是shell命令
			expr index "KevinBlandy" "B" 



