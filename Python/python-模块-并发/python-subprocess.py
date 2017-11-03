----------------------------
subprocess					|
----------------------------
	* 开启子进程,用于执行系统命令
	* 用于子进程管理

----------------------------
subprocess-属性				|
----------------------------
	PIPE

----------------------------
subprocess-函数				|
----------------------------
	Popen()
		* 开启一个子进程,执行一个shell命令,返回一个 Popen 对象
		* 参数可以是数组,第一个元素是命令,剩下的则是命令参数
			Popen(["mkdir","t1"])
		* 参数也可以直接就是一个命令
			Popen("mkdir t2", shell=True)
		* 关键字参数
			shell
				* bool 值
			stdout	
				* 枚举值,指定命令执行后输出内容的管道
				* subprocess.PIPE		:由子进程转到父进程

	str getoutput(commond)
		* 执行SHELL命令,返回执行后的结果

	tuple getstatusoutput(commond)
		* 执行shell命令,返回元组,第一个数据是状态int值,第二个数据是执行后的结果

----------------------------
subprocess.Popen			|
----------------------------
	* 实例函数
		stdout.read()
			* 读取执行结果,返回的结果是字节数据类型
			* 参数传递字符串,表示编码类型,不传默认使用系统编码
	* demo
		import subprocess
		popen = subprocess.Popen('dir',shell=True,stdout=subprocess.PIPE)
		# stdout=subprocess.PIPE 会把子进程执行的结果数据,传递给父进程.最终赋值给执行后的对象.如果没有该参数,会直接打印,而返回对象不能读取到执行结果
		result = popen.stdout.read()
		print(str(result,'GBK'))