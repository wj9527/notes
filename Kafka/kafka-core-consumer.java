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
	
	# 订阅主题与分区(api)
	
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
	# 位移提交
	# 控制或者关闭消费
	# 指定位移消息
	# 负载均衡
	# 多线程消费
	