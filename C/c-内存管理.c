----------------------
内存分区			  |
----------------------
	# GCC编译过程
		1,预处理
			* 宏定义展开,头文件展开,条件编译,注释删除
		2,编译
			* 语法检查,将预处理后文件编程成汇编文件
		3,汇编
			* 将汇编文件生成目标文件(二进制文件)
		4,链接
			* C语言写的程序需要依赖各种库
			* 编译之后还需要把库链接到最终可执行程序中去
			* 在 linux 里面使用命令: 'ldd [可执行文件]'  ,可以列出来该程序运行需要的动态库
			* 在 windows 下可以使用 Dependency Walker 去列出可执行文件运行需要的动态库
	
	# 在Linux下查看编译后文件的内存分区
		size Demo.out

		text    data     bss     dec     hex filename
		1224     548       4    1776     6f0 Demo.out

		text
			* 代码区,只读,函数
			* 加载的可执行代码段,所有可执行代码都加载到代码区,这块内存是只读的,不能在运行期间修改

		data
			* 初始化的数据:全局变量,静态变量,文字常量区(只读)
			* 加载的是可执行文件数据段
			* 存储于数据段(全局初始化,静态初始化数据,文字常量(只读))的数据的生命周期为整个程序运行过程

		bss
			* 没有初始化的数据:全局变量,静态变量
			* 加的是可执行文件BSS段,位置可以分开也可以紧靠数据段
			* 存储于数据段的数据(全局未初始化,静态未初始化数据)的生命周期为整个程序过程

		* 在程序执行之前,有几个内存分区已经确定,但是没有加载
			* text,data,bss

		* 运行程序,加载内存,首先会根据前面确定的内存分区先加载
			* text,data,bss
			* 还会额外加载两个区:一个栈,一个堆
		
		* 栈区(stack)
			* 普通局部变量,自动管理内存
			* 先进后出
		
		* 堆区(heap)
			* 手动申请空间,需要手动释放,整个程序结束,系统也会自动的回收
			* 位于BSS区和栈区之间
		
	
	# 栈越界
		* 递归,耗光了栈内存的空间的
		* 查看系统(linux)栈空间的大小
			ulimit -a

			core file size          (blocks, -c) 0
			data seg size           (kbytes, -d) unlimited
			scheduling priority             (-e) 0
			file size               (blocks, -f) unlimited
			pending signals                 (-i) 15088
			max locked memory       (kbytes, -l) 64
			max memory size         (kbytes, -m) unlimited
			open files                      (-n) 65535
				* 最多只能打开 65535个文件
			pipe size            (512 bytes, -p) 8
			POSIX message queues     (bytes, -q) 819200
			real-time priority              (-r) 0
			stack size              (kbytes, -s) 8192
				* 栈的大小是 8192 字节,就是 1024 KB ,也就是 1 MB
			cpu time               (seconds, -t) unlimited
			max user processes              (-u) 15088
			virtual memory          (kbytes, -v) unlimited
			file locks                      (-x) unlimited

			
