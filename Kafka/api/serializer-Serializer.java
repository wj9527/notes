-------------------
Serializer<T>	   |
-------------------
	# 消息的序列化接口
	# 抽象方法
		void configure(Map<String, ?> configs, boolean isKey);

		byte[] serialize(String topic, T data);
			*  执行序列化操作

		default byte[] serialize(String topic, Headers headers, T data) {
			return serialize(topic, data);
		}

		@Override
		void close();