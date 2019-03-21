----------------------------
Topic						|
----------------------------
	
----------------------------
主题管理					|
----------------------------
	# 创建主题
		 bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
			--zookeeper
				* 指定zk
			--partitions
				* 分区数量
			--replication-factor
				* 每个分区的副本数量
			--topic
				* 主题名称,一般不要用下划线开头,因为kafka内部的主题使用下划线开头
			--replica-assignment
				* 可以自己控制分区的分配
				* 这种方式根据分区号的数值大小按照从小到大的顺序进行排列,分区与分区之间用逗号","隔开
				* 分区内多个副本用冒号":"隔开
				* 并且在使用该参数创建主题时不需要原本必备的 partitions 和 replication-factor 这两个参数
				* 同一个分区内的副本不能有重复,比如指定了 0:0,1:1 这种,就会报出异常
				
				--replica-assignment 2:0,0:1,1:2,2:1

				2:0 表示第 0 个分区,有两个副本,在broker.id 为 2 和 0 的节点上
				0:1 表示第 1 个分区,有两个副本,在broker.id 为 0 和 1 的节点上
				1:2 表示第 2 个分区,有两个副本,在broker.id 为 1 和 2 的节点上
				2:1 表示第 3 个分区,有两个副本,在broker.id 为 2 和 1 的节点上

			--config
				* 自定义配置,覆盖主题的默认配置
				* 该配置项可以存在多个,表示覆盖多个值
					--config kek=value
					--config cleanup.policy=compact --config max.message.bytes=l000
				* 可以在zookeeper的节点下查看这些数据:
					get /config/topics/[topic-name]

			
			--if-not-exists
				* 如果主题已经存在,不会抛出异常,也不会创建成功
	
	# 可以通过 ZooKeeper 客户端来获取 broker分区副本的分配情况
		get /brokers/topics/[主题名]

		{"version":1,"partitions":{"2":[1,2]}}

		partitions:
			* 表示当前主题的分区"2",有两个副本,分配在了border.id 等于 1 和 2 的节点上
			* json对象的key表示主题的分区编号,value数组表示该分区的副本都分配在哪些broker节点上
				
		
		
	# 查看主题详情
		bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test
			--zookeeper
				* 指定zk
			--describe
				* 查看详情指令
			--topic
				* 主题名称
		
		Topic(主题名):test      PartitionCount(分区数量):1        ReplicationFactor(每个分区副本数):1     Configs(主题的配置信息):
        Topic: test     Partition(分区号): 0    Leader(当前分区Lader副本所在节点的broker.id): 2       Replicas(当前分区所有副本所在节点的broker.id - AR): 2     Isr(当前分区的ISR集合 - ISR): 2
		
		Replicas
			* 当前这个分区都在哪些节点上
	
	# 查看创建主题时设置的参数(--config)
		get /config/topics/[主题名]

		{
			"version":1,
			"config":{
				"max.message.bytes":"10000",
				"cleanup.poliy":"compact"
			}
		}

		* config 表示设置的一个或者多个配置项

	# replica分配算法考虑机房(0.10.x)
		* 可以配置一个参数broker.rack说明当前broker在哪个机房
		* 算了,用到的时候再去查吧