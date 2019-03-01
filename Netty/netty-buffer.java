--------------------
Buffer				|
--------------------
	# 涉及的类库
		ReferenceCounted
			|-ByteBuf
				|-CompositeByteBuf
			|-ByteBufHolder
			|-FileRegion
				|-DefaultFileRegion
		ByteBufProcessor
		ByteBufAllocator
		Unpooled
		ByteBufUtil

	# Netty 缓冲 API 提供了几个优势
		* 可以自定义缓冲类型
		* 通过一个内置的复合缓冲类型实现零拷贝
		* 扩展性好,比如 StringBuilder
		* 读取和写入索引分开,不需要调用 flip() 来切换读/写模式
		* 方法链
		* 引用计数
		* Pooling(池)
	
	# Netty 使用 reference-counting(引用计数)的时候知道安全释放 Buf 和其他资源
	
	# ByteBuf 的默认最大容量限制是 Integer.MAX_VALUE
	
	# Buffer的相关的库
		ByteBufAllocator
			|-PooledByteBufAllocator
			|-UnpooledByteBufAllocator
		Unpooled
		CompositeByteBuf
		ByteBufUtil
		ByteBufHolder
	
	# ByteBuf的类型
		————————————————————————————————————————
					池化的				非池化的
		直接缓冲		
		堆缓冲
		————————————————————————————————————————

--------------------
Buffer				|
--------------------
	capacity	总大小
	readindex	读角标
	wirteindex	写角标

--------------------
Heap Buffer(堆缓冲区)|
--------------------
	# 最常用的类型是 ByteBuf 将数据存储在 JVM 的堆空间
		* 提供了直接访问数组的方法,通过 ByteBuf.array()来获取 byte[]数据
	
	# 访问非堆缓冲区 ByteBuf 的数组会导致 UnsupportedOperationException
		* 可以使用 ByteBuf.hasArray()来检查是否支持访问数组
	
------------------------
Direct Buffer(直接缓冲区)|
------------------------
	# 在堆之外直接分配内存,直接缓冲区不会占用堆空间容量
	# 直接缓冲区的缺点是在分配内存空间和释放内存时比堆缓冲区更复杂,而 Netty 使用内存池来解决这样的问题,这也是 Netty 使用内存池的原因之一
	# 直接缓冲区不支持数组访问数据,但是可以间接的访问数据数组
		ByteBuf directBuf = Unpooled.directBuffer(16);
		// 直接缓冲区
		if(!directBuf.hasArray()){
			// 可读的数据长度
			int len = directBuf.readableBytes();
			// 创建相同长度的数组
			byte[] arr = new byte[len];
			// 把缓冲区的数据读取到数组
			directBuf.getBytes(0, arr);
		}



