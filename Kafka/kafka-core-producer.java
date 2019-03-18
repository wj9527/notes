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
	
	# 消息的序列化(编码器)
		* 需要用到序列化,把消息(key和value)序列化为byte[]
		* 最顶层的序列化接口:Serializer<T>
			byte[] serialize(String topic, T data);
		* 设置消息编码器(必须设置的属性)
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

	
	# 分区器
		* 消息send()到broker时可能会经过拦截器,序列化器(编码器),分区器之后才会被发送到broker
		* 如果消息对象(ProducerRecord)指定了partition属性值,那么就不需要分区器了,因为已经指定好了
		* 如果未指定partition属性值,那么就要依赖于分区器,根据key字段来计算出partition值
		* 分区器接口:Partitioner
			void configure(Map<String, ?> configs)
			int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster);
			void close();
		
		* Kafka提供默认的分区器实现
			DefaultPartitioner
				* 如果key不为null,那么会对key进行hash(采用MurmurHash2算法,具备高运算性能和低碰撞率)计算
				* 根据最终的hash值来计算分区号
				* 也就是说相同的key会被发送到同一个分区(如果扩展了partition的数量那么就不能保证了)
				* 计算得到的分区号会是所有分区中的任意一个,可能会选择到不可用的分区
			
				* 如果Key为null,那么消息会被以轮询的方式发送到每个可用的分区
				* 计算得到的分区号仅为可用分区中的任意一个,不会选择到不可用的分区
				
		* 使用自定义的分区器
			properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
			
	
	# 拦截器
		* 拦截器可以在消息被发送之前做一些其他的工作,例如:过滤,数据的修改
		* 也可以用来在发送回调逻辑前做一些定制化的需求,例如:统计
		* 拦截器接口:ProducerInterceptor<K,V>
			void configure(Map<String, ?> configs);
			ProducerRecord<K, V> onSend(ProducerRecord<K, V> record);
			void onAcknowledgement(RecordMetadata metadata, Exception exception);
			void close();
		
		* Kafka会在消息编码,分区计算之前调用拦截器的onSend方法

		* 一般来说最好不要修改消息的:topic,partition,key等信息
		* 除非确保有准确的判断,否则可能会出现于预想结果出现偏差的可能
		* 不仅可能会影响分区的计算,还可能印象broker端日志的压缩功能
		
		* Kafka会在消息被应答(send api返回)之前或者消息发送失败时调用拦截器的onAcknowledgement方法
		* 一样的,需要判断 exception 是否为null,从而确定消息是否发送成功
		* 它会优先于 Callback 执行
		* 这个方法运行在Producer的I/O线程中,所以越简单越好,否则会影响消息的发送速度

		* close()方法主要用在关闭拦截器时执行一些资源的清理工作

		* 拦截器中几个方法,在执行中抛出的异常,都会被捕获并记录到日志
		* 但是不会向上传递(不会抛出)
		
		* 拦截器可以配置多,形成一个责任链(责任链不多解释)
		* 如果拦截链中某一个拦截器抛出了异常,它会被跳过
		* 下一个拦截器会接着上一个执行成功的拦截器开始执行(略过执行异常的拦截器)
		* 多个拦截器使用逗号分隔

		* 配置拦截器
			properties.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "io.javaweb.kafka.interceptor.SimpleInterceptor");
		
		* 配置拦截器链
			properties.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "SimpleInterceptor1,SimpleInterceptor1");

		