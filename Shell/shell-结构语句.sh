---------------------
if then				 |
---------------------
	# 语法
		if cmd 
		then
			cmds
		fi


		* cmd 其实并不是一个 bool表达式
		* 严格来说,它是一个shell命令
		* 这个命令的结束状态码如果是0,就会执行 cmds 
	
	# 更加优雅的语法
		if cmd; then
			coms
		fi

		* 在cmd后放置一个分号,就可以把then写在后面了
			if pwd > /dev/null;then
				echo "success"
			fi
	
---------------------
if then else		 |
---------------------
	# 单个else语法
		if cmd; then
			coms
		else
			coms
		fi
	
	# 多个elseif语法
		if cmd; then
			coms
		elif cmd; then
			coms
		else
			coms
		fi
	
	

	# 小练习
		testuser="kevinblandy"
		if grep ${testuser} /etc/passwd; then
			echo "用户 :${testuser} 在系统中存在"
		elif ls -d /home/${testuser}; then
			echo "用户 :${testuser} 在系统中不存在"
			echo "但是存在他的家目录"
		else
			echo "用户不存在，也没有他的家目录"
		fi
	

---------------------
test				 |
---------------------
	# if then 语句中,根据shell命令返回的状态码来确定是否要执行代码块
	# 但是if then不能判断命令状态码之外的条件,此时可以使用 test 指令
		if test condition;then
			cmds
		fi
		
		* condition 是一系列的参数和值
		* 其实condition是把计算结果的true转换为了状态码0,把false转换为了非0
	
	# 如果忽略 condition,那么它就会以非0状态退出,执行else块
		if test; then
			echo "success"
		else
			echo "error"	# 执行
		fi
	
	# 测试变量是否有内容
		var=" "
		test var	# false

		* 全是空白字符串的值test运算结果为false
	
	
	# 更加优雅的写法,使用[]来代替 test 指令
		if [ condition ]; then
			echo "success"
		else
			echo "error"
		fi

		* 注意了,第一个方括号之后,和第二个方括号之前必须添加一个空格
	
	# test 命令可以判断三类条件
		* 数值的比较
		* 字符串的比较
		* 文件的比较

	
	# 不能在test命令中使用浮点数

----------------------------
if-then 的高级特性			|
----------------------------
	# 用于数学表达式的双括号
		* 表达式
			(( expression ))
		
		* 除了test运算可以使用的运算符以为,在(())里面还可以使用的运算符
			val++				后增
			varl--				后减	
			++val				先增
			--val				先减
			!					求反
			~					位反
			**					幂运算
			<<					左位移
			>>					右位移
			&					位移与
			|					位移或	
			&&					逻辑和
			||					逻辑或
	
		*  双括号中的><运算不需要转义
			val=10
			if (( $val ** 2 > 90 )); then
				(( val1=$val ** 2 ))	# 双括号赋值
				echo "the square of ${val} is ${val1}"
			fi
		
		* 不仅可以在if语句中用双括号命令,也可以在脚本的普通命令中使用双括号来赋值
			val=10
			(( val1=$val ** 2 ))
			echo ${val1}
		
		* 这种特性就是,可以在条件里面使用各种表达式,不是所有的shell都支持这种双括号要注意

	# 用于高级字符串处理功能的双方括号
		* 表达式
			[[ expression ]]
		
		* expression 使用 test命令中采用的标准字符串比较
		* 还提供了另一个特性:模式匹配
			reg="r*"	# 正则表达式
			if [[ ${USER} == ${reg} ]] ; then
				echo "Hello ${user}"
			fi
		
		
		* 这种特性就是可以在[[]]里面使用正则表达式
		* 不是所有的shell都支持双方括号


----------------------------
case						|
----------------------------
	# 语法(玄学的语法)
		case variable in 
		pattern | pattern) 
			command;;
		pattern) 
			command;;
		*) 
			default command;;
		esac

		variable:表示变量
		pattern:表示一个只,可以有多个,使用|分割
		command:如果值匹配上了就执行的命令
		default command:都没匹配上时执行的语句
	
		
		* 代码块中,最后一行代码末尾需要添加两个分号
		* demo
			val="c"
			case ${val} in
			"a") 
				echo "匹配到了"
				echo "a";;
			"b" | "c") 
				echo "匹配到了"
				echo "b or c";;
			*) 
				echo "都不是";;
			esac 


----------------------------
for							|
----------------------------
	# 语法
		for var in list
		do
			commands
		done

		var :临时的迭代变量
			* 它会一直存在(值是最后一次迭代值)直到脚本终止(js一样)
			* 当然,可以主动的修改这个值

		list :多个迭代的数据
			* 默认情况下使用以下字符作为分隔符:空格,制表符,换行

		commands: 每次迭代执行的代码
	
	# 也可以把 do 关键字放在第一行
		for var in list; do
			commands
		done
		
		——————————————————————————————————
		for var in 1 2 3 "Hello"; do
			echo "value=${var}"
		done

		value=1
		value=2
		value=3
		value=Hello
		——————————————————————————————————
	
	# list数据中有引号的问题
		for var in I don't know if this'll work; do
			echo "word:${var}"
		done

		word:I
		word:dont know if thisll	# 两个单引号之间的内容,当作一个内容输出了
		word:work

		* shell尝试把列表中的单引号内容看做是一份单独的数据

		* 可以给单引号添加转义字符
			for var in I don\'t know if this\'ll work; do
				echo "word:${var}"
			done
		
		* 也可以使用双引号来定义用到单引号的值
			for var in I "don't" know if "this'll" work; do
				echo "word:${var}"
			done

	# list数据中有空格的问题
		for var in Hello World; do
			echo "word:${var}"
		done
		word:Hello
		word:World	# 因为以空格分割,所以,会被拆分为2个词儿

		* 可以使用双引号把含有空格的内容括起来
			for var in "Hello World"; do
				echo "word:${var}"
			done
	
	# 当一个迭代项,两边都有"号时,shell并不会把"当作值的一部分(见上述案例)
		* 如果要输出",请使用转义
			for var in \"Hello\" \"World\"; do
				echo "word:${var}"
			done
	

	# 从变量读取列表
		list="KevinBlandy is a pythonista"
		list="${list} !"
		for var in $list; do
			echo "word:${var}"
		done

	# 从命令读取值
		file="demo.sh"
		for var in `cat ${file}`; do
			echo "word:${var}"
		done
	
	# 更改字段的分隔符
		* 默认使用的是:换行,空格,制表符
		* 在脚本中添加代码,指定唯一的默认分隔符
			IFS=$'\n'	# 指定分隔符为换行,那么空格和制表符不会起作用

		* 如果代码量很大,需要在第一个地方修改IFS值,然后忽略这次修改
		* 让脚本的其他地方继续使用IFS的默认值,可以使用一个比较安全简单的操作
			
			IFS.OLD=$IFS	# 先记录原始值
			IFS=$'\n'		# 修改分隔符
			IFS=$IFS.OLD	# 在完成迭代操作后,还原为原始值
		
		
		* 使用冒号(:)分割的值(比如:/etc/passwd 文件)
			IFS=:
			value="H:e:l:l:o"
			for var in ${value}; do
				echo "word:${var}"
			done
		
		* 使用多个IFS字符
			IFS=$'\n':;" #使用换行,冒号,分号,双引号 作为分隔符
		
	# 用通配符读取目录
		for file in /usr/local/*; do
			if [ -d ${file} ]; then
				echo "目录：${file}"
			else
				echo "文件：${file}"
			fi
		done

		* 如果不在末尾添加 *,那么路径就会被当作一个字符串
		* 也可以添加多个路径,合并为一个list进行迭代
			for file in /usr/local/* /usr/*; do
				if [ -d ${file} ]; then
					echo "目录：${file}"
				else
					echo "文件：${file}"
				fi
			done
		
		* 就算是目录/文件不存在,for也会尝试去处理列表中的内容
		* 所以在执行操作之前,尽量先判断一下目录/文件是否存在

