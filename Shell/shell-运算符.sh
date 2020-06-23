--------------------------------
数学运算符						|
--------------------------------
	+ - * / %

	# 原生的bash不支持数学运算,可以通过其他的命令来实现
		awk
		expr(常用)
	
	# 乘号必须要用转义符
		val=`expr 4 \* 5`
	
	# 在[]中进行计算
		val=$[1-5]
		echo $val

		* $[...]是以前的语法，也可以做整数运算，不建议使用。
	
	# 在 (()) 中进行计算
		((r = 1 + 3))
		echo ${r} # 4

		val=$((5 * 20))
		echo ${val} # 100
		
		* 这个语法只能计算整数，否则会报错。
		* (...))会自动忽略内部的空格
		* 语法支持的算术运算符如下。
			+：加法
			-：减法
			*：乘法
			/：除法（整除）
			%：余数
			**：指数
			++：自增运算（前缀或后缀）
			--：自减运算（前缀或后缀
		
		* $((...))的圆括号之中，不需要在变量名之前加上$，不过加上也不报错
		* 如果在$((...))里面使用字符串，Bash 会认为那是一个变量名。
		* 如果不存在同名变量，Bash 就会将其作为空值，因此不会报错，而$((...))会将空值当作0
			v1=1
			v2=$((v1 + 1))
			echo ${v2} # 2
		
		* 内部可以用圆括号改变运算顺序
		* 支持以下的二进制位运算符。
			<<：位左移运算，把一个数字的所有位向左移动指定的位。
			>>：位右移运算，把一个数字的所有位向右移动指定的位。
			&：位的“与”运算，对两个数字的所有位执行一个AND操作。
			|：位的“或”运算，对两个数字的所有位执行一个OR操作。
			~：位的“否”运算，对一个数字的所有位取反。
			^：位的异或运算（exclusive or），对两个数字的所有位执行一个异或操作
		
		* 支持10进制, 8进制, 16进制
		* 指定进制
			base#number # base进制的number
			val=$((4#22))
			echo ${val} # 10
		
		* 逻辑运算，支持以下的逻辑运算符。
			<：小于
			>：大于
			<=：小于或相等
			>=：大于或相等
			==：相等
			!=：不相等
			&&：逻辑与
			||：逻辑或
			!：逻辑否
			expr1?expr2:expr3：三元条件运算符。若表达式expr1的计算结果为非零值（算术真），则执行表达式expr2，否则执行表达式expr3。
				v1=1
				v2=2
				v3=$((v1 > v2 ? v1 : v2))
				echo ${v3}

			* 逻辑表达式为真，返回1，否则返回0。
		
		* $((...))支持的赋值运算符，有以下这些。
			echo $((a=1))	# 1
			echo ${a}		# 1

			parameter = value：简单赋值。
			parameter += value：等价于parameter = parameter + value。
			parameter -= value：等价于parameter = parameter – value。
			parameter *= value：等价于parameter = parameter * value。
			parameter /= value：等价于parameter = parameter / value。
			parameter %= value：等价于parameter = parameter % value。
			parameter <<= value：等价于parameter = parameter << value。
			parameter >>= value：等价于parameter = parameter >> value。
			parameter &= value：等价于parameter = parameter & value。
			parameter |= value：等价于parameter = parameter | value。
			parameter ^= value：等价于parameter = parameter ^ value。
		
		* 逗号,在$((...))内部是求值运算符，执行前后两个表达式，并返回后一个表达式的值。
			echo $((foo = 1 + 2, 3 * 4))	# 12
			echo $foo						# 3
	
	# 除法运算符的返回结果总是整数
		
	
		
	
--------------------------------
数值比较						|
--------------------------------
	 -eq
		* 是否相等,相等返回 true
		[ 5 -eq 5 ]
	 -ne
		* 是否相等,不相等返回 true
	 -gt
		* 左 > 右 返回 true
	 -lt
		* 左 < 右 返回 true
	 -ge
		* 左 >= 右 返回 true
	 -le
		* 左 <= 右 返回 true
	
	*  此类运算符只支持数字,不支持字符串(除非字符串是数字,会被自动的转换)
		if [ 1 -eq "1" ] ;then
			echo "yes"
		fi
--------------------------------
复合运算						|
--------------------------------	
		!
			* 非运算
			[ ! -d "$ZOO_DATADIR" ]
		-o
			* 或(or)运算 |
		-a
			* 与(and)运算 &
	
		&& 
			* 逻辑的AND
			[ condition ] &&  [ condition ]

			if w && NotCommand; then
				echo "不会执行的"
			fi

		||
			* 逻辑OR
			[ condition ] ||  [ condition ]

--------------------------------
字符串比较						|
--------------------------------
	=
		* 字符串相等,返回true
		[ "1" = "1" ]
		
	!=
		* 字符串不相等,返回true
		[ "1" != "2" ]

	-z
		* 如果是“blank”字符串，返回 true
			[ -z "" ]			=> true
			[ -z "    " ]		=> true

	-n
		* 是否，不是空字符串
		[ -n "1" ]
	
	>
	<
		* 判断字符串的大小,比较的是ascii码
		* 大于小于号注意要转义(不然就会被认为是重定向)
	
	$	
		* 检测字符串是否为 blank，不为空返回 true
			val="			  "
			if [ ${val} ]; then
				echo "不为空：${val}" 
			fi
	

	str
		* 直接把字符串当作bool运算
		* 字符串非空,返回true
			[ " " ]	# false
			[ "" ]	# false

--------------------------------
文件运算符						|
--------------------------------
	-b
		* 是否是设备块文件
		[ -b "/usr/local/foo.conf" ]
	-c
		* 是否字符串设备文件
	-d
		* 是否是目录
		[ -d "/usr/local/" ]
	-f
		* 是否是普通文件(不是目录,不是设备)
	-g
		* 是否设置了SGID
	-k
		* 是否设置了粘着位(Sticky Bit)
	-r
		* 判断文件是否可读
	-p
		* 检测文件是否具名管道
	-u
		* 是否设置了suid位
	-w
		* 是否可写
	-s
		* 文件是否为空,size=0
	-e
		*  检测文件/目录是否存在
	-x
		* 是否存在并可执行
	-O
		* ..
	-G
		* ..
	-nt
		* 文件1是否比文件2新
		[ f1 -nt f2 ]
	-ot	
		* 文件1是否比文件2旧
		[ f1 -ot f2]

	* 如果目录名称或者文件名称包含空格(在Linux下命名合法)
	* 那么就要用双引号把文件名称括起来,不然会异常
		[ -d "${file}" ]

	* 可以一次性判断多个条件
		if [ -f $FILE_NAME -o -d $FILE_NAME ]
		
--------------------------------
expr							|
--------------------------------
	# 表达式计算工具,支持算术运算，可以不使用((...))语法。

	V1=1
	V2=2
	SUM=`expr $V1 + $V2`
	echo $SUM	# 3
	echo `expr ${V1} - ${V2}` # -1
	echo `expr $V1 % $V2`	# 1

	# expr命令也不支持非整数参数


