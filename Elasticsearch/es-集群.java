----------------------------
集群						|
----------------------------
	# 文档
		https://www.elastic.co/guide/en/elasticsearch/reference/current/discovery-settings.html	
	
	# ES隐藏了分布式系统中的很多复杂特性
		* 数据分片机制, 数据会被分片为多个shard, 分布式的存储在不同的节点
		* 集群发现机制, 可以有新节点的加入和退出
		* shard负载均衡, shard 要平均的分配到不同的节点
		* shard的重新分配, 在节点增减的时候, 会自动的均衡每个节点的 shard
	
	# Shard
		* Primary Shard, 分布式存储
			* 一个doc肯定存在于一个 Primary Shard
			* Primary Shard在创建的时候就已经确定, 不能修改, 默认有5个Primary Shard

		* Replica Shard, 副本
			* 每个Primary Shard 默认有1个 Replica Shard, 可以index创建后随意的修改
		
		* Master Shard负责处理读写请求, Replica Shard仅仅负责读请求
		* Master Shard和Replica Shard不能在同一个节点, 如果节点宕机, 主从都不能使用, 不能容错
		* Master Shard宕机, 某个Replica Shard会自动成为Master Shard
	
	# 集群节点之间的数据同步是多线程异步的
		* Replica节点在更新的时候, 使用_version乐观锁来保证数据的一致性


----------------------------
相关的端点					|
----------------------------
	GET /_cluster/health
	GET /_cat/health?
	GET /_cat/nodes?v







