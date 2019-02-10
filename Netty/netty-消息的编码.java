--------------------------------
消息的编码						|
--------------------------------

--------------------------------
LengthFieldPrepender			|
--------------------------------
	# 主动给消息添加长度信息的头部
		public LengthFieldPrepender(int lengthFieldLength)
		public LengthFieldPrepender(int lengthFieldLength, boolean lengthIncludesLengthFieldLength) 
		public LengthFieldPrepender(int lengthFieldLength, int lengthAdjustment)
		public LengthFieldPrepender(int lengthFieldLength, int lengthAdjustment, boolean lengthIncludesLengthFieldLength)
		public LengthFieldPrepender(ByteOrder byteOrder, int lengthFieldLength, int lengthAdjustment, boolean lengthIncludesLengthFieldLength)

		
		lengthIncludesLengthFieldLength
			* length字段值是否要包含当length字段本身的长度
		
		lengthFieldLength
			* 用几个字节表示数据的长度
		
		lengthAdjustment
			* lengthAdjustment + 数据长度取值 = 数据长度字段之后剩下包的字节数
			* 添加到长度字段的补偿值(0)
			* 对于某些协议,长度字段还包含了消息头的长度,在这种应用场景中,往往需要使用lengthAdjustment进行修正
			* 由于整个消息(包含消息头)的长度往往大于消息体的长度,所以它要设置为负数(数据长度字段的长度取负)