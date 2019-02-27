-------------------------
引用计数				 |
-------------------------
	# 引用计数
		* 被引用计数包含的对象,能够显示的被垃圾回收,当初始化的时候,如果计数被减少到0则对象会被显示回收
		* 再次访问被回收的这些对象将会抛出异常
		*  如果一个对象实现了ReferenceCounted,并且包含有其他对象也实现来ReferenceCounted,当这个对象计数为0被回收的时候,所包含的对象同样会通过release()释放掉

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
	# 对于 ReferenceCounted 的操作工具类
		
	