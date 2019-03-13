--------------------------------
kafka 维护						|
--------------------------------
	# 启动Kafka
		kafka-server-start.bat ../../config/server.properties
	
	# 关闭
		kafka-server-stop.sh
	
	# 创建主题
		 bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
			--create
				* 创建指令
			--zookeeper
				* 指定zk的地址
			--partitions
				* 创建多少个partition
			--replication-factor
				* 每个partition多少个备份
			--topic
				* 设置topic的名称
			
	# 查看创建的主题
		bin/kafka-topics.sh --zookeeper localhost:2181 --list
	
	# 查看主题的详情
		bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test

		Topic:test      PartitionCount:1        ReplicationFactor:1     Configs:
        Topic: test     Partition: 0    Leader: 2       Replicas: 2     Isr: 2
	
	# 删除主题
		bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic test
		

	# 打开基于控制台的消息生产者	
		bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
			--broker-list
				* 指定集群中任意节点的地址信息
			--topic
				* 指定要往哪个topic写入数据
			

	# 打开基于控制台的消息消费者
		bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
			--bootstrap-server
				* 指定集群中节点的信息
			--topic
				* 知道要消费哪个节点
			
	