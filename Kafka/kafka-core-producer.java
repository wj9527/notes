------------------------
producer				|
------------------------
	# Maven
		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka-clients</artifactId>
			<version>2.1.1</version>
		</dependency>
	
	# 消息生产者的逻辑
		1. 配置客户端参数,以及根据参数创建相应的消息生产者实例
		2. 构建消息
		4. 发送消息
		5. 关闭客户端(生产者实例)

	# 基本的发送演示
		Properties properties = new Properties();
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("client.id", "producer.client.id.demo");
		
		try(KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties)){
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test", "你好啊");
			kafkaProducer.send(producerRecord);
		}

	# 异常的可重试机制
		* KafkaProducer 中 一般会发生两种类型的异常 : 可重试的异常和不可重试的异常 
		* 常见的可重试异常
			NetworkException(网络异常,这个有可能是由于网络瞬时故障而导致的异常,可以通过重试解决)
			LeaderNotAvailableException(分区的 leader 副本不可用,这个异常通常发生在 leader 副本下线而新的 leader 副本选举完成之前,重试之后可以重新恢复)
			UnknownTopicOrPartitionException
			NotEnoughReplicasException
			NotCoordinatorException

		* 常见的不可重试异常
			RecordTooLargeException(发送的消息太大,不会执行重试,直接抛出)

		* 如果配置了 retries 参数,那么只要在规定的重试次数内自行恢复了,就不会抛出异常(retries 参数的默认值为 0)
			props.put(ProducerConfig.RETRIES_CONFIG, 10);
	
	# 消息的序列化
		* 需要用到序列化,把消息序列化为byte[]
		* 最顶层的序列化接口:Serializer<T>


	# 消息的 key 和 partition 可选
		* partition没填
			* key有填按照key进行哈希
			* 相同key去一个partition(如果扩展了partition的数量那么就不能保证了)
		
		* key没填
			* round-robin(轮询)来选partition
	
	# produce都是批量请求,会积攒一批,然后一起发送,不是调send()就进行立刻进行网络发包
		* 发往同一个partition的请求按照配置,攒一波
		* 然后由一个单独的线程一次性发过去

