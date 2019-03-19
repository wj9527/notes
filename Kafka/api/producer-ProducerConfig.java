------------------------
ProducerConfig			|
------------------------
	# 消息生产者配置
	# 提供了N多的配置项(它们都静态类变量存在,大写,.转换为下划线,并且以 CONFIG 结尾)
		bootstrap.servers (BOOTSTRAP_SERVERS_CONFIG)
		client.dns.lookup
		buffer.memory
			* 消息累加器(RecordAccumulator)的缓存大小
			* 默认值为:33554432kb = 32MB

		retries
			* 对于可重试的异常,如果配置了 retries 参数,那么只要在规定的重试次数内自行恢复了,就不会抛出异常(默认值为 0)
		
		acks
		compression.type
		batch.size
			* 在 RecordAccumulator 中 BufferPool 会缓存的 ByteBuffer 大小
			* BufferPool 只针对特定大小的 ByteBuffer 进行管理,而其他大小的 ByteBuffer 不会缓存进 BufferPool 中
			* 默认:16384B,即 16KB

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
			* 在send()api被阻塞的时候(缓冲区满了,没有空间存放消息,一般发生在消息生产速度,大于消息的发送速度)
			* 最多阻塞多少时间(毫秒),超过该时间就会抛出异常
			* 默认为:60000(60s)

		request.timeout.ms
		metadata.max.age.ms
		metrics.sample.window.ms
		metrics.num.samples
		metrics.recording.level
		metric.reporters
		max.in.flight.requests.per.connection
			* 消息从RecordAccumulator发送到broker的请求,会被缓存到 InFlightRequests 中,直到响应
			* 该配置限制每个连接(也就是客户端与 Node 之间的连接)最多缓存的请求数
			* 该请求就是,把消息已经从缓存中发出去了,但是还没收到响应的请求
			* 默认值为5,也就是说,最多缓存5个未响应的请求,一旦超过该值,就不能往这个连接发送更多的请求了
			* 除非缓存中的请求,收到了响应

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