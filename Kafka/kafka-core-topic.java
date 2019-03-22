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

	# 
		