--------------------------------
kafka 维护						|
--------------------------------
	# 启动Kafka
		kafka-server-start.bat ../../config/server.properties
	
	# 关闭
		kafka-server-stop.sh
	
	# 创建主题
		 bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
		
	# 打开基于控制台的消息生产者	
		bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
	
	# 打开基于控制台的消息消费者
		bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
	

	