------------------------
Stream					|
------------------------
	# redis5的新特性, 类似于一个MQ系统, Stream是Redis的数据类型中最复杂的

	# 添加数据到stream
		XADD [stream] [id] [key] [value] [key] [value]
			stream
				* 指定stream名称
			id
				* 指定消息id, 如果需要系统生成, 可以使用: * (自增长)
				* 如果是自定义id, 在这种情况下, 最小ID为: 0-1, 并且命令不接受等于或小于前一个ID的ID
			
			key, value
				* Stream条目不是简单的字符串, 而是由一个或多个键值对组成的
				* 可以有多个key,value
			
			* demo
				XADD mystream * sensor-id 1234 temperature 19.8
				// 往名为mystream的Stream中添加了一个条目sensor-id: 123, temperature: 19.8，使用了自动生成的条目ID，也就是命令返回的值
			
			* 该命令返回消息的id
				1574131352064-0

				* id由2部分组成, 一部分是当前服务器的时间戳, 一部分就是自增(相同时间戳会自增)的序列id
				* 由于ID与生成条目的时间相关, 因此可以很容易地按时间范围进行查询


	# 获取stream中的条目数量
		XLEN [stream]


	# 查询数据
		 XRANGE [stream] [start] [end] COUNT [count]
			start, end
				* 开始时间戳和结束时间戳(id的前一部分)
					XRANGE mystream 1518951480106 1518951480107
				* 两个特殊的符号:-,+ 分别表示可能的最小ID和最大ID
			
			COUNT [count]
				* 返回的数量, 非必须参数
					XRANGE mystream - + COUNT 1
			
			* 返回的每个条目都是有两个元素的数组: ID和键值对列表
				1) 1) "1574131352064-0" [0][0]
				   2) 1) "sensor-id"	[0][1]
					  2) "1234"
					  3) "temperature"
					  4) "19.8"
				2) 1) "1574132679725-0"	[1][0]
				   2) 1) "sensor-id"	[1][1]
					  2) "12345"
					  3) "temperature"
					  4) "19.81"
			
			* 通过选择最后一条记录的id, 进行迭代
				XRANGE mystream 1574132679725-0 + COUNT 2
			
			* XRANGE的查找复杂度是O(log(N)), 因此O(M)返回M个元素, 这个命令在count较小时, 具有对数时间复杂度
			* 这意味着每一步迭代速度都很快, 所以XRANGE也是事实上的流迭代器并且不需要XSCAN命令
	
	# 逆向查询数据
		XREVRANGE [stream] [end] [start] COUNT [count]

		* 与XRANGE相同, 但是以相反的顺序返回元素
		* 因此XREVRANGE的实际用途是检查一个Stream中的最后一项是什么
			XREVRANGE [stream] + - COUNT 1
	

	# 监听消费
		XREAD COUNT [count] BLOCK [milliseconds] STREAMS [stream...] ID [id...]

			COUNT [count]
				* 非必须的参数
			
			BLOCK [milliseconds]
				* 非必须的参数
				* 如果添加该参数, 表示当前客户端阻塞消费
				* 参数是毫秒数, 如果为0, 表示不超时
					
			STREAMS [stream...]
				* 指定stream, 可以有多个, 空格分割
				* 注意, 如果有多个 stream, 则必须要有多个 id, 一一对应, 表示同时从不同的Stream中读取不同key的数据
			
			ID [id...]
				* 非必须的参数
				* 指定消费的大于该id(后半部分即可)的消息, 可以有多个
					XREAD COUNT 2 STREAMS mystream 1574131352064-0
				
				* 注意, 如果有多个 id, 则 STREAMS 必须指定多个stream, 一一对应, 表示同时从不同的Stream中读取不同key的数据
					STREAMS mystream otherstream 0 0
				
				* id的特殊值: $ , 这个特殊的ID意思是XREAD应该使用流已经存储的最大ID作为最后一个ID, 以便我们仅接收从我们开始监听时间以后的新消息
				* 这在某种程度上相似于Unix命令tail -f(阻塞消费)
					XREAD BLOCK 0 STREAMS mystream $

				* 可以一次性监听N个stream的n个id, 只要其中一个有新的数据, 就会返回
					XREAD BLOCK 0 STREAMS mystream foo $ $
	
				
	# 创建消费组
		XGROUP [CREATE key groupname id-or-$] [SETID key id-or-$] [DESTROY key groupname] [DELCONSUMER key groupname consumername]
		XGROUP CREATE [stream] [group] [id]
			[stream]
				* 指定stream的名称
			
			[group]
				* 指定group的名称
			
			[id]
				* 表示从大于哪条消息开始消费
				* 如果设置为了0, 表示从头开始消费, 设置为 $ , 只会消费在消费组创建以后的消息
			
			

			* 创建一个消费组(目前创建消费组, stream必须存在)
				 XGROUP CREATE mystream mygroup $
			
	
	
	# 消费组的形式监听消费
		XREADGROUP GROUP [group] [consumer] COUNT [count] BLOCK [milliseconds] STREAMS [stream...] ID [id...]
			GROUP [group]
				* 指定消费组的名称
			
			consumer
				* 指定消费组中, 当前消费者的名称
			
			ID [id...]
				* 消费组中的id, 有一个特殊符号: >
				* 这个特殊的ID只在消费者组的上下文中有效, 其意思是: 到目前为止从未传递给其他消费者的消息
				* 如果id设置为了:> 表示, 仅仅消费当前消费组中, 其他消费者未消费的消息, 通过这个特殊id,达到: 同一个消费组中, 只有一个消费组可以消费消息


			* 跟 XREAD 一样, 多了俩参数: GROUP [group], consumer
			* 一个消息, 一次性只能被同一个消费组中一个消费者消费
		
	# 消费组中的消费者, 确认消息消费
		 XACK [stream] [greoup] ID [ID ...]
			[stream]
				* 指定stream
			
			[greoup]
				* 指定group
			
			ID [ID ...]
				* 指定多个id, 表示已经消费
		
	

	