--------------
Java
--------------
	# 数据类型
		double			double
		float			float
		int32			int
			* 使用变长编码, 对于负值的效率很低
			* 如果字段有可能有负值, 请使用 sint64 替代

		uint32			int
			* 变长字段

		uint64			long
			* 变长字段

		sint32			int
			* 使用变长编码, 这些编码在负值时比 int32 高效的多

		sint64			long
			* 使用变长编码, 有符号的整型值编码时比通常的int64高效

		fixed32			int
			* 总是4个字节, 如果数值总是比总是比228大的话, 这个类型会比uint32高效

		fixed64			long
			* 总是8个字节, 如果数值总是比总是比256大的话, 这个类型会比uint64高效

		sfixed32		int
			* 总是4个字节
		sfixed64		long
			* 总8个字节
		bool			boolean
		string			String
			* 一个字符串必须是UTF-8编码或者 7-bit ASCII编码的文本
		bytes			ByteString
			
	# 默认值
		* 如果字段非必须的(可以出现0次), 那么默认值
		string	默认是一个空string
		bytes	默认是一个空的bytes
		bool	默认是false
		数值	默认是0
		枚举	默认是第一个定义的枚举值, 必须为0
		对于消息类型(message), 域没有被设置, 确切的消息是根据语言确定的
	
