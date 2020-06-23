------------------------
常用的关键字			|
------------------------
	echo
		# 打印文字到屏幕 
		# 参数
			-n
				* 在末尾不添加换行符
			-e
				* 会解释引号中的转义字符（就是让转义字符生效）
	type
		* 用户返回某个指令的类型
			type echo
			echo is a shell builtin (echo 是shell内置脚本)

			type ls
			ls is /usr/bin/ls  (type 是 /usr/bin 目录下的程序)
		
		* 参数
			-a
				* 查看命令的所有定义
					type -a echo (echo命令即是内置命令，也有对应的外部程序)
					echo is a shell builtin
					echo is /usr/bin/echo
			-t
				* 返回数据的类型
					alias		别名
					keyword		关键字(例如: type -t if)
					function	函数
					builtin		内置命令
					file		文件
				* 如果不是这几种类型, 则不会返回任何东西
			

	exec
		执行另一个 Shell 脚本 

	read
		读标准输入 
	
	source 
		* 执行另一个脚本, 通常用于重新加载一个配置文件。
		* 最大的特点是在当前 Shell 执行脚本, 不像直接执行脚本时，会新建一个子 Shell
		* 所以, source命令执行脚本时, 不需要export变量

		* 可以通过这方式导入其他外部脚本的变量, 函数

		* 简写, 可以使用一个点（.）来表示
			. .bashrc

	alias 
		* 为一个命令指定别名
			alias search=grep # 为grep命令起一个search的别名。

		* 可以用来为长命令指定一个更短的别名
			$ alias today='date +"%A, %B %-d, %Y"'
			$ today # Tuesday, June 23, 2020

		* 为了防止误删除文件，可以指定rm命令的别名
			alias rm='rm -i'
		* 可以接受参数，参数会直接传入原始命令
		* 直接调用alias命令，可以显示所有别名
			alias
		
		* unalias命令可以解除别名
			unalias lt
		
	expr
		对整数型变量进行算术运算 

	test
		用于测试变量是否相等,是否为空,文件类型等 

	exit
		退出
	
	sleep
		线程暂停N秒
	