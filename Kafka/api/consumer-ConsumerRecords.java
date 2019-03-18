--------------------------------
ConsumerRecord<K,V>				|
--------------------------------
	# 消费者消费的消息对象
	# 构造函数
		ConsumerRecord(String topic,int partition,long offset,K key,V value)
		ConsumerRecord(String topic,int partition,long offset,long timestamp,TimestampType timestampType,Long checksum,int serializedKeySize,int serializedValueSize,K key,V value,Headers headers)
		ConsumerRecord(String topic,int partition,long offset,long timestamp,TimestampType timestampType,Long checksum,int serializedKeySize,int serializedValueSize,K key,V value,Headers headers,Optional<Integer> leaderEpoch)
		ConsumerRecord(String topic,int partition,long offset,long timestamp,TimestampType timestampType,long checksum,int serializedKeySize,int serializedValueSize,K key,V value)

		timestampType
			* 时间戳的类型,枚举值
				NO_TIMESTAMP_TYPE(-1, "NoTimestampType") // 没时间戳
				CREATE_TIME(0, "CreateTime")			//  消息创建时间
				LOG_APPEND_TIME(1, "LogAppendTime");	//  追加到日志文件的时间
		
		headers
			* 消息头,可以用于传输业务数据以外的其他信息
			* Headers是一个接口,实现类:RecordHeaders

	# 实例属性
		private final String topic;						// 主题
		private final int partition;					// 分区号
		private final long offset;						
		private final long timestamp;					// 时间戳
		private final TimestampType timestampType;
		private final int serializedKeySize;
		private final int serializedValueSize;
		private final Headers headers;					// 消息头
		private final K key;							// 键
		private final V value;							// 值
		private final Optional<Integer> leaderEpoch;
		private volatile Long checksum;

	# 实例方法
		