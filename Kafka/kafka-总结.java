------------------------
总结					|
------------------------
	# Producer端直接连接broker.list列表,从列表中返回TopicMetadataResponse,该Metadata包含Topic下每个partition leader建立socket连接并发送消息
	# Broker端使用zookeeper用来注册broker信息,以及监控partition leader存活性
	# Consumer端使用zookeeper用来注册consumer信息,其中包括consumer消费的partition列表等,同时也用来发现broker列表,并和partition leader建立socket连接,并获取消息