------------------------
jvm						|
------------------------
	# 参考
		https://blog.csdn.net/antony9118/article/details/51375662
		https://blog.csdn.net/coderlius/article/details/79272773
	
	# HotSpot VM
		


------------------------
一般线程分析步骤		|
------------------------
	1. JSP 确定Java的进程ID: 18392
		
	2. 使用jstack导出java的线程列表
		jstack -l 18392 > 18392.stack

	3. 使用top -Hp 18392 -d1 命令查看java进程里的子线程的实际占用
		* 记录id后和导出的stack文件比对, 就能知道具体的是哪里占用
		* 在线程列表里面,线程的的id是以16进制表示的, 名称叫做:nid
			nid=0x47d8
	

	# 可以使用专业的在线线程dump分析平台
		https://fastthread.io/

