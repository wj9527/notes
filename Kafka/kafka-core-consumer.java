------------------------
consumer				|
------------------------
	# 订阅topic是以一个消费组来订阅的，一个消费组里面可以有多个消费者
		* 同一个消费组中的两个消费者,不会同时消费一个partition
		* 通俗理解就是一个partition,只能被消费组里的一个消费者消费,但是可以同时被多个消费组消费
		* 如果消费组内的消费者如果比partition多的话,那么就会有个别消费者一直空闲
	
	# 订阅topic时,可以用正则表达式,如果有新topic匹配上,那能自动订阅上

	# Consumer均衡算法
		* 当一个group中,有consumer加入或者离开(宕机,关闭)时,会触发partitions负载均衡
		* 负载均衡的最终目的,是提升topic的并发消费能力
			1 假如topic1,具有如下partitions: P0,P1,P2,P3
			2 加入group中,有如下consumer: C0,C1
			3 首先根据partition索引号对partitions排序: P0,P1,P2,P3
			4 根据consumer.id排序: C0,C1
			5 计算倍数:(partitions的数量除以consumer的数量)
				M = [P0,P1,P2,P3].size / [C0,C1].size,本例值 M = 2(向上取整)

			6 然后依次分配partitions: 
				C0 = [P0,P1]
				C1 = [P2,P3]
				
				Ci = [P(i * M),P((i + 1) * M -1)]
				
				i = 0
				m = 2
				P(i * m) = P0
				P((i + 1) * m - 1) = P1
	
	# offset的保存
		* 0.10版本后,kafka把这个offset的保存,从zk总剥离,保存在一个名叫__consumeroffsets topic的topic中
		* 写进消息的key由groupid,topic,partition组成,value是偏移量offset
		* topic配置的清理策略是compact,总是保留最新的key,其余删掉
		* 一般情况下,每个key的offset都是缓存在内存中,查询的时候不用遍历partition
		* 如果没有缓存,第一次就会遍历partition建立缓存,然后查询返回
		* 确定consumer group位移信息写入__consumers_offsets的哪个partition,具体计算公式:
			__consumers_offsets partition = Math.abs(groupId.hashCode() % groupMetadataTopicPartitionCount)   
			
			* groupMetadataTopicPartitionCount由'offsets.topic.num.partitions'指定,默认是50个分区
		



		