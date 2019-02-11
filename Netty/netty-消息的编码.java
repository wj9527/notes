----------------------------
编码						|
----------------------------
	# 编码就是把对象转换为网络传输的二进制数据(ByteBuf)
	# 涉及类库
		|-MessageToByteEncoder<I>
			|-MessageToMessageEncoder<I>
				|-LengthFieldPrepender
				|-StringEncoder
