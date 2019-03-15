------------------------
producer				|
------------------------
	# 消息的 key 和 partition 可选
		* partition没填
			* key有填按照key进行哈希
			* 相同key去一个partition(如果扩展了partition的数量那么就不能保证了)
		
		* key没填
			* round-robin(轮询)来选partition
	
	# produce都是批量请求,会积攒一批,然后一起发送,不是调send()就进行立刻进行网络发包
		* 发往同一个partition的请求按照配置,攒一波
		* 然后由一个单独的线程一次性发过去