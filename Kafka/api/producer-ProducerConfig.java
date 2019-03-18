------------------------
ProducerConfig			|
------------------------
	# 消息生产者配置
	# 提供了N多的配置项(它们都静态类变量存在,大写,.转换为下划线,并且以 CONFIG 结尾)
		bootstrap.servers (BOOTSTRAP_SERVERS_CONFIG)
		client.dns.lookup
		buffer.memory
		retries
			* 对于可重试的异常,如果配置了 retries 参数,那么只要在规定的重试次数内自行恢复了,就不会抛出异常(默认值为 0)
		
		acks
		compression.type
		batch.size
		linger.ms
		delivery.timeout.ms
		client.id
		send.buffer.bytes
		receive.buffer.bytes
		max.request.size
		reconnect.backoff.ms
		reconnect.backoff.max.ms
		retry.backoff.ms
		max.block.ms
		request.timeout.ms
		metadata.max.age.ms
		metrics.sample.window.ms
		metrics.num.samples
		metrics.recording.level
		metric.reporters
		max.in.flight.requests.per.connection
		key.serializer
		value.serializer
			* 设置key/value的编码器
			* 值是编码器的类路径

		connections.max.idle.ms
		partitioner.class
			* 设置分区器的实现类

		interceptor.classes
			* 设置拦截器的实现类

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
		enable.idempotence
		transaction.timeout.ms
		transactional.id

	
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