----------------------------
Topic						|
----------------------------
	# 主题的创建
		* 主题相关的配置,其实在broker都有默认的配置项和值
		* 如果主题没有进行特殊的设置,那么就会使用broker默认的

	# 主题的删除
		* 主题被删除后,不会立即的从磁盘删除,只是被标记为删除
		* 如果broker配置的参数: delete.topic.enable=false 那么执行删除不会有任何的效果
		* 如果要删除的主题是 Kafka 的内部主题,那么删除时就会报错

		* 使用 kafka-topics.sh 脚本删除主题的行为本质上只是在 ZooKeeper 中的 /admin/delete_topics 路径下创建一个与待删除主题同名的节点
		* 以此标记该主题为待删除的状态
		* 与创建主题相同的是,真正删除主题的动作也是由 Kafka 的控制器负责完成的
		* 说白了,可以通过操作zookeeper的节点来完成主题的删除操作

		* 还可以手动的方式来删除主题,主题的元数据存储在zookeeper的路径:
			/brokers/topics
			/config/topics
		* 主题中的消息数据存储在磁盘 log.dir 或 log.dirs 配置的路径下,只需要手动删除这些地方的内容即可
			1. 先删除zk中的元数据
				rmr /config/topics/[主题名称]
				delete /brokers/topics/[主题名称]
				delete /config/topics/[主题名称]
			2. 再删除日志文件
				rm - rf /tmp/kafka-logs/[主题名称]＊
		
		* 删除主题是一个不可逆的操作

----------------------------
Topic 优先副本的选举		|
----------------------------
	# partition集群中只有leader节点可以对外提供io服务
		* follower节点只负责同步leader节点的数据
		* 如果leader节点宕机,那么该parition就会变得不可用

		* 此时就会从follower节点中选择一个节点,成为新的leader节点
		* 当原来的leader节点恢复以后,加入集群中时,它只是成为了一个新的follower节点
	
	# 优先副本(Preferred Replica)
		* 优先副本是指在AR集合列表中的第一个副本
		* 理想的情况下,优先副本就是该parition的leader副本

		* 优先副本的选举就是指通过一定的方式,促使优先副本选举为Leader副本
		* 以此来促进集群的负载均衡,这个行为也叫做分区平衡
	
	# 分区自动平衡
		* broker端参数:auto.leader.rebalance.enable(默认值为 true)
		* 在开启的情况下,Kafka的控制器会启动一个定时任务
		* 该任务会轮询所有的broker节点,计算每个broker节点的分区不平衡率
		
		* broker中的不平衡率 = 非优先副本的leader个数 / 分区(parition)总数
		* broker中的不平衡率是否超过了指定参数的比值
			leader.imbalance.per.broker.percentage(默认值: 10%)
		* 如果超过了,就会自动执行优先副本的选举动作,以求分区平衡
		* 执行周期由指定的参数控制
			leader.imbalance.check.interval.seconds(默认 300s)

	# 生产环境不建议开启:auto.leader.rebalance.enable
		* 这可能引起负面的性能问题,也可能引发客户端一定时间的阻塞
		* 因为它的执行时间无法自主掌控
		
		* 如果在关键时期,执行关键任务的时候,执行了优先副本的选举操作
		* 此时会阻塞业务,频繁超时

		* 所以,这个可以手动的去控制
	
	# 手动的对分区leader副本进行重新平衡
		* 使用脚本:kafka-preferred-replica-election.sh
		* 优先副本的选举是一个安全的过程,Kafka客户端可以自动感知到分区leader副本的变更
		
		* 手动通过脚本执行leader副本重新平衡
			kafka-preferred-replica-election.sh --zookeeper localhost:2181	

			--path-to-json-file
				* 可以通过该参数指定一个JSON文件
				* 该JSON文件保存需要执行优先副本选举的parition清单(而不是执行所有分区)
					{
						"paritions":[{
							"parition":0,
							"topic":"topic-paritions"
						},{
							"parition":1,
							"topic":"topic-paritions"
						}]
					}

		
		* 这种方式默认会把集群中的所有分区都执行一遍优先副本的选举操作
		* leader副本转移是一项高成本的工作,如果要执行的分区数很多,那么对客户端会造成一定的影响

		* 如果集群包含了大量的分区,那么上面的这种使用方式可能会失效
		* 在优先副本的选举过程中,具体的元数据会存入zk节点:/admin/preferred_replica_election
		* 受限于zk单个节点的数据大小限制(默认1mb),那么可能会选举失败
	
		
		* 生产环境一般都是使用 path-to-json-file来分批,手动的执行优先副本的选举操作
		* 而且要注意时间段,尽量的避开业务高峰
	
	# 通俗的解释
		* topic的每个paritio会分布在不同的broker上
		* 一般每个broker上的leader数目相同是最好的,因为只有leade负责io,大家都一样,就负载均衡了

		* 如果某个leader宕机了,那么它的follwer会成为新的leader,就可能导致,某个broker上有俩leader节点,导致负载不均衡

		* 可以开启分区的自动平衡机制,程序定时扫描整个集群,自动完成leader的重新选举,来维护集群的负载均衡
		* 执行自动平衡(优先副本选举)的这个过程比较耗时,而且还会阻塞客户端,不可控(因为不知道啥它啥时候就会执行分区的自动平衡)

		* 可以手动的去执行脚本,来完成分区的自动平衡,手动的方式自己可以控制执行的时间,以及需要平衡的分区(针对性的执行,而不是一次性的执行所有)



		

		