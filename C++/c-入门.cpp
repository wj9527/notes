--------------------------------
C语言-环境搭建					|
--------------------------------


--------------------------------
C语言-main函数定义				|
--------------------------------
	int main()
	{
		return 0;
	}

--------------------------------
C语言的构成						|
--------------------------------
	# 关键字
		auto		extern		short		while
		break		float		signed		_Alignas
		case		for			sizeof		_Alignof
		char		goto		static		_Atomic
		const		if			struct		_Bool
		continue	inline		switch		_Complex
		default		int			typedef		_Generic
		double		register	void		_Static_assert
		enum		return		volatile	_Thread_local

	
	# 控制语句(9)
		if else 
		for
		while
		do while
		continue
		break
		switch
		goto
		return
	
	# 运算(34)
		

--------------------------------
C语言-编译						|
--------------------------------
	# 语法
		gcc hello.cpp
		
		* 在windows平台默认生成一个 a.exe 文件
		* 在Linux平台默认生成一个 a.out 文件

	# 参数
		-o
			* 指定名称,参数与值之间使用空格
		
		-std
			* 指定编译的C标准,枚举值参数与值之间使用=号
				c99		使用c99标准
				c1x		使用c11以前的标准
				c11		使用c11标准
		


--------------------------------
C语言-头文件					|
--------------------------------
	# 头文件的本质其实就是把指定的文件'copy'到当前文件的include处
	# 头文件的导入
		#include<xx.h>
	
	# 系统标准的头文件
		Windows 
			C:\MinGW\include

		Linux
			/usr/include
	

--------------------------------
C语言-printf 转换符				|
--------------------------------
	%a	浮点数,十六进制数和P计数法
	%A	浮点数,十六进制数和P计数法
	%c	单个字符
	%d	有符号十进制整数
	%e	浮点数,e计数法
	%E	浮点数,e计数法
	%f	浮点数,十进制计数法
	%g	根据值的不同,自动选择 %f 或者 %e,%e格式用于指数小余-4或者大于等于精度时
	%G	根据值的不同,自动选择 %f 或者 %e,%e格式用于指数小余-4或者大于等于精度时
	%i	有符号的十进制整数(与%d相同)
	%o	无符号八进制整数
	%p	指针
	%s	字符串
	%u	无符号十进制整数
	%x	无符号十六进制整数,使用十六进制数0f
	%X	无符号十六进制整数,使用十六进制数0F
	%%	打印一个百分号

--------------------------------
C语言-printf 转换说明修饰符		|
--------------------------------
	# 在%和转换字符之间插入修饰符,可以修饰基本的转换说明
	# 标记
		-			
		+
		空格
		#
		0

	# 数字
	# .数字
	# 转换
		h
		hh
		j
		l
		ll
		L
		t
		z
		
