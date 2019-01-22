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
	