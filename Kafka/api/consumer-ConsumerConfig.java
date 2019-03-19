-----------------------------
ConsumerConfig				 |
-----------------------------
	# 消息消费者配置
	# 配置项
		bootstrap.servers
			* kafka集群的节点
			* ip:port
			* 连接多个使用逗号分隔

		client.dns.lookup
		group.id
			* 设置消费组,默认值为空字符串

		session.timeout.ms
		heartbeat.interval.ms
		partition.assignment.strategy
			* 消费者与topic之间的分区分配策略

		metadata.max.age.ms
		enable.auto.commit
		auto.commit.interval.ms
		client.id
			* 当前消费者的id
			* 如果不设置,会自动的生成一个非空字符串(consumer-[编号])
				consumer-1....consumer-x

		max.partition.fetch.bytes
		send.buffer.bytes
		receive.buffer.bytes
		fetch.min.bytes
		fetch.max.bytes
		fetch.max.wait.ms
		reconnect.backoff.ms
		reconnect.backoff.max.ms
		retry.backoff.ms
		auto.offset.reset
		check.crcs
		metrics.sample.window.ms
		metrics.num.samples
		metrics.recording.level
		metric.reporters
		key.deserializer
		value.deserializer
			* 设置value和key的解码器

		request.timeout.ms
		default.api.timeout.ms
		connections.max.idle.ms
		interceptor.classes
		max.poll.records
		max.poll.interval.ms
		exclude.internal.topics
		internal.leave.group.on.close
		isolation.level
		security.protocol
		ssl.protocol
		ssl.provider
		ssl.cipher.suites
		ssl.enabled.protocols
		ssl.keystore.type
		ssl.keystore.location
		ssl.keystore.password
		ssl.key.password
		ssl.truststore.type
		ssl.truststore.location
		ssl.truststore.password
		ssl.keymanager.algorithm
		ssl.trustmanager.algorithm
		ssl.endpoint.identification.algorithm
		ssl.secure.random.implementation
		sasl.kerberos.service.name
		sasl.kerberos.kinit.cmd
		sasl.kerberos.ticket.renew.window.factor
		sasl.kerberos.ticket.renew.jitter
		sasl.kerberos.min.time.before.relogin
		sasl.login.refresh.window.factor
		sasl.login.refresh.window.jitter
		sasl.login.refresh.min.period.seconds
		sasl.login.refresh.buffer.seconds
		sasl.mechanism
		sasl.jaas.config
		sasl.client.callback.handler.class
		sasl.login.callback.handler.class
		sasl.login.class

	# 构造函数
		ProducerConfig(Properties props)
		ProducerConfig(Map<String, Object> props)
	
	# 静态方法
		Map<String, Object> addSerializerToConfig(Map<String, Object> configs,Serializer<?> keySerializer, Serializer<?> valueSerializer)
		Properties addSerializerToConfig(Properties properties,Serializer<?> keySerializer,Serializer<?> valueSerializer)
		Set<String> configNames()
			* 返回可配置的key

		void main(String[] args)
			* main函数,以html格式打印配置和说明
		