-------------------------
引用计数				 |
-------------------------
	# 接口 ReferenceCounted

	# 接口方法
		int refCnt();
			* 返回引用数

		ReferenceCounted retain();
			* 添加一个引用

		ReferenceCounted retain(int increment);
			* 添加N个引用

		ReferenceCounted touch();
		ReferenceCounted touch(Object hint);

		boolean release();
			* 释放一个引用

		boolean release(int decrement);
			* 释放n个引用


-------------------------
ReferenceCountUtil		 |
-------------------------
	