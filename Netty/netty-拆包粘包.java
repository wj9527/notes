----------------------------
拆包和粘包的解决方案		|
----------------------------
	# Netty 提供了一个可扩展的类 ByteToMessageDecoder 
	# 只需要覆写方法
		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
			if (in.readableBytes() < 4) {
				// 消息长不足4,不能解码
				return; 
			}
			// 可以成功解码了一个消息
			out.add(in.readBytes(4)); 
		}

		* 每当有新数据接收的时候,ByteToMessageDecoder 都会调用 decode() 方法来处理内部的累积缓冲
		* decode() 方法可以决定当累积缓冲里没有足够数据时可以往 out 对象里放任意数据
		* 当有更多的数据被接收了 ByteToMessageDecoder 会再一次调用 decode() 方法
		* 如果在 decode() 方法里增加了一个对象到 out 对象里,这意味着解码器解码消息成功
		* ByteToMessageDecoder 将会丢弃在累积缓冲里已经被读过的数据
		* 不需要对多条消息调用 decode(),ByteToMessageDecoder 会持续调用 decode() 直到不放任何数据到 out 里
	
	# 现成提供的编码器
		LineBasedFrameDecoder
			* 回车换行符作为消息结束符的TCP黏包的问题

		LengthFieldBasedFrameDecoder
			* 固定长度

		DelimiterBasedFrameDecoder
			* 以指定的符号分割
				
		FixedLengthFrameDecoder
			* 固定头部的长度
	
----------------------------
LengthFieldBasedFrameDecoder|
----------------------------
	# 专门为固定包头提供的解码器
		public LengthFieldBasedFrameDecoder(int maxFrameLength,int lengthFieldOffset, int lengthFieldLength)
		public LengthFieldBasedFrameDecoder(int maxFrameLength,int lengthFieldOffset, int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip)
		public LengthFieldBasedFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip, boolean failFast)
		public LengthFieldBasedFrameDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,int lengthAdjustment, int initialBytesToStrip, boolean failFast)


		
		maxFrameLength
			* 单个包最大的长度,这个值根据实际场景而定

		lengthFieldOffset
			* 表示数据长度字段开始的偏移量
			* 第几个字节开始,是表示数据长度的(0)
		
		lengthFieldLength
			* 数据长度字段的所占的字节数
		
		lengthAdjustment
			* lengthAdjustment + 数据长度取值 = 数据长度字段之后剩下包的字节数
			* 添加到长度字段的补偿值(0)
			* 对于某些协议,长度字段还包含了消息头的长度,在这种应用场景中,往往需要使用lengthAdjustment进行修正
			* 由于整个消息(包含消息头)的长度往往大于消息体的长度,所以它要设置为负数(数据长度字段的长度取负)

		initialBytesToStrip
			* 表示从整个包第一个字节开始,向后忽略的字节数(0)
			* 可以设置该参数,来忽略掉包头信息,仅仅留下数据包体,给下一个Handler处理
	
	# 场景1
		lengthFieldOffset   = 0
		lengthFieldLength   = 2		// 通用的前面2字节表示数据长度
		lengthAdjustment    = 0
		initialBytesToStrip = 0 

		BEFORE DECODE (14 bytes)         AFTER DECODE (14 bytes)
		+--------+----------------+      +--------+----------------+
		| Length | Actual Content |----->| Length | Actual Content |
		| 0x000C | "HELLO, WORLD" |      | 0x000C | "HELLO, WORLD" |
		+--------+----------------+      +--------+----------------+
	
	# 场景2
		 lengthFieldOffset   = 0
		 lengthFieldLength   = 2
		 lengthAdjustment    = 0
		 initialBytesToStrip = 2	// 丢弃前面2字节的包头

		 BEFORE DECODE (14 bytes)         AFTER DECODE (12 bytes)
		 +--------+----------------+      +----------------+
		 | Length | Actual Content |----->| Actual Content |
		 | 0x000C | "HELLO, WORLD" |      | "HELLO, WORLD" |
		 +--------+----------------+      +----------------+
	
	# 场景3
		 lengthFieldOffset   =  0
		 lengthFieldLength   =  2
		 lengthAdjustment    = -2 // 长度头表示的长度,包含了自身头部的长度
		 initialBytesToStrip =  0

		 BEFORE DECODE (14 bytes)         AFTER DECODE (14 bytes)
		 +--------+----------------+      +--------+----------------+
		 | Length | Actual Content |----->| Length | Actual Content |
		 | 0x000E | "HELLO, WORLD" |      | 0x000E | "HELLO, WORLD" |
		 +--------+----------------+      +--------+----------------+
	
	# 场景4
		 lengthFieldOffset   = 2	// 表示消息长度的头,不在首部
		 lengthFieldLength   = 3
		 lengthAdjustment    = 0
		 initialBytesToStrip = 0

		 BEFORE DECODE (17 bytes)                      AFTER DECODE (17 bytes)
		 +----------+----------+----------------+      +----------+----------+----------------+
		 | Header 1 |  Length  | Actual Content |----->| Header 1 |  Length  | Actual Content |
		 |  0xCAFE  | 0x00000C | "HELLO, WORLD" |      |  0xCAFE  | 0x00000C | "HELLO, WORLD" |
		 +----------+----------+----------------+      +----------+----------+----------------+
	
	# 场景5
		 lengthFieldOffset   = 0
		 lengthFieldLength   = 3
		 lengthAdjustment    = 2	// 整个消息体的长度,还要包含一个头部的长度,因为后面还有一个头部
		 initialBytesToStrip = 0

		 BEFORE DECODE (17 bytes)                      AFTER DECODE (17 bytes)
		 +----------+----------+----------------+      +----------+----------+----------------+
		 |  Length  | Header 1 | Actual Content |----->|  Length  | Header 1 | Actual Content |
		 | 0x00000C |  0xCAFE  | "HELLO, WORLD" |      | 0x00000C |  0xCAFE  | "HELLO, WORLD" |
		 +----------+----------+----------------+      +----------+----------+----------------+

	# 场景6
		 lengthFieldOffset   = 1 (= the length of HDR1)			// 第2个字节表示数据长度
		 lengthFieldLength   = 2								
		 lengthAdjustment    = 1 (= the length of HDR2)			// 除此之外,还有1个字节的消息头
		 initialBytesToStrip = 3 (= the length of HDR1 + LEN)	// 移除前面3个字节的数据

		 BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
		 +------+--------+------+----------------+      +------+----------------+
		 | HDR1 | Length | HDR2 | Actual Content |----->| HDR2 | Actual Content |
		 | 0xCA | 0x000C | 0xFE | "HELLO, WORLD" |      | 0xFE | "HELLO, WORLD" |
		 +------+--------+------+----------------+      +------+----------------+
	
	# 场景7
		 lengthFieldOffset   =  1
		 lengthFieldLength   =  2
		 lengthAdjustment    = -3 (= the length of HDR1 + LEN, negative)
		 initialBytesToStrip =  3

		 BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
		 +------+--------+------+----------------+      +------+----------------+
		 | HDR1 | Length | HDR2 | Actual Content |----->| HDR2 | Actual Content |
		 | 0xCA | 0x0010 | 0xFE | "HELLO, WORLD" |      | 0xFE | "HELLO, WORLD" |
		 +------+--------+------+----------------+      +------+----------------+