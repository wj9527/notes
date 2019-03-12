------------------------
负载均衡				|
------------------------
	# Producer均衡算法
		* kafka集群中的任何一个broker,都可以向producer提供metadata信息
		* 这些metadata中包含"集群中存活的servers列表"/"partitions leader列表"等信息(请参看zookeeper中的节点信息)
		* 当producer获取到metadata信息之后, producer将会和Topic下所有partition leader保持socket连接
		
		* 消息由producer直接通过socket发送到broker,中间不会经过任何"路由层"
		* 事实上,消息被路由到哪个partition上,是有producer客户端决定
		* 比如可以采用"random","key-hash","轮询"等
		* 如果一个topic中有多个partitions,那么在producer端实现"消息均衡分发"是必要的
		* 在producer端的配置文件中,可以指定partition路由的方式
	

