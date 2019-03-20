------------------------
consumer				|
------------------------
	# 每个消费者都有一个消费组,一个消费组有N多个消费者

	# 一个消费组可以消费多个分区的消息

	# 每一个分区的消息,只能被一个消费组中的一个消费者消费

	# 如果消费组中的消费者数量大于了分区的数量,这样的会导致多出去的消费者一直处于空闲状态
	
	# 通俗的理解
		* 一个topic可以被多个消费组消费,并且这些消费组消费的都是相同的数据
		* topic对于消费组来说,是广播,一条消息会发送到多个消费者组

		* 消费组消费的是topic
		* 消费topic的时候,topic里面的所有分区,都会均匀的分布到消费组里面的消费者身上
		* 一条来自于分区的消息,只能被一个消费者消费
		* 消息对于消费组来说,是单播,一条消息只能被消费组中的一个消费者消费
	
	# Maven
		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka-clients</artifactId>
			<version>2.1.1</version>
		</dependency>
	
	# 消费逻辑
		1. 配置消费者客户端的参数
		2. 根据参数创建消费者实例对象
		3. 订阅主题
		4. 拉取消息进行消费
		5. 提交消费位移
		6. 关闭消费者实例

	# 基本的消费
		Properties properties = new Properties();
		properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("group.id", "group.demo");

		try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties)) {
			kafkaConsumer.subscribe(Arrays.asList("test"));
			while (true) {
				ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));
				for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
					String value = consumerRecord.value();
					System.out.println(value);
				}
			}
		}
	
	# 消费者必须属于一个消费者
		* 通过 group.id 属性设置
			properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group.demo");
		* 如果不设置,默认为空字符串
		* 一般而言这个值的设置要有一定的业务意义
	
	# 主题订阅
		* 相关api
			Set<String> subscription()
			void subscribe(Collection<String> topics)
			void subscribe(Collection<String> topics, ConsumerRebalanceListener listener)
			void subscribe(Pattern pattern)
			void subscribe(Pattern pattern, ConsumerRebalanceListener listener)
		
		* 订阅主题,如果方法被重复的调用,那么以最后一次调用的为准
		* 如果使用正则表达式的方法(Pattern)订阅了主题,就算是主题不存在也可以,在主题被创建后,符合条件的主题会被自动的订阅
		* 负载均衡监听器:ConsumerRebalanceListener 
			 void onPartitionsRevoked(Collection<TopicPartition> partitions);
			 void onPartitionsAssigned(Collection<TopicPartition> partitions);

		* 使用这种方式进行订阅消息具有自动负载均衡的功能
		* 在多个消费者的情况下,可以根据分区分配策略来自动分配各个消费者与分区的关系
		* 在消费组内消费者的增加/减少,分区分配关系会自动的跳转,以及实现故障的自动转移
	
	# 分区订阅
		* 相关api
			void assign(Collection<TopicPartition> partitions)
			Set<TopicPartition> assignment()

		* TopicPartition 对象用于描述分区和主题
			private int hash = 0;			//hash值
			private final int partition;	// 分区编号
			private final String topic;		// 主题信息
			TopicPartition(String topic, int partition)

		* 这种方式订阅,不具备自动的负载均衡功能
	
	# 主题订阅模式的互斥性
		* 集合的订阅方式:AUTO_TOPICS
			subscribe(Collection<String> topics)
		* 正则表达式的订阅方式:AUTO_PATTERN
			subscribe(Pattern pattern)
		* 指定分区的订阅方法:AUTO_ASSIGNED
			assign(Collection<TopicPartition> partitions)

		* 这三种订阅方法不能同时存在,只能选择一种,不然会抛出异常
	
	# 消息的解码器
		* 通过 key.deserializer/value.deserializer 设置key/value的解码器
			properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"");
			properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"");
		
		* 解码器接口:Deserializer
			void configure(Map<String, ?> configs, boolean isKey);
				* 配置
			T deserialize(String topic, byte[] data);
				* 解码消息
			default T deserialize(String topic, Headers headers, byte[] data) {
				return deserialize(topic, data);
			}	
				* 可以获取到消息头的解码方法

			@Override
			void close();

		* 如果有多个,使用逗号分隔
	
	# 消息消费
		* 消息消费模式为主动从topic拉取消息,这是一个不断轮询的过程
		* 相关api
			ConsumerRecords<K, V> poll(final Duration timeout)
		* 该值的设置取决于程序对响应速度的要求
		* 如果设置为0,poll会立即返回而不管是否拉取到了数据
		* 如果线程的工作仅仅是为了拉取数据,那么该值可以设置为 Long.MAX_VALUE


	# 控制或者关闭消费
	# 指定位移消息
	# 负载均衡
	# 多线程消费
	