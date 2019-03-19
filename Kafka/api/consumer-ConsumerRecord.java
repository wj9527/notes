------------------------
ConsumerRecord<K, V>	|
------------------------
	# 消费者消费的消息对象
	# 静态属性
		public static final long NO_TIMESTAMP = RecordBatch.NO_TIMESTAMP;
		public static final int NULL_SIZE = -1;
		public static final int NULL_CHECKSUM = -1;
	
	# 构造函数
		KafkaConsumer(Map<String, Object> configs)
		KafkaConsumer(Map<String, Object> configs,Deserializer<K> keyDeserializer,Deserializer<V> valueDeserializer)
		KafkaConsumer(Properties properties)
		KafkaConsumer(Properties properties,Deserializer<K> keyDeserializer,Deserializer<V> valueDeserializer)
	