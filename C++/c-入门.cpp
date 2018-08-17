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
	





	
		