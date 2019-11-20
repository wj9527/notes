------------------------
Stream	- 命令总结		|
------------------------
	XADD 
		* XADD key ID field string [field string ...]
		* 添加一条消息到stream, 如果该命令携带了 MAXLEN 参数, 那么stream是定长的
		
	XDEL
		* XDEL key ID [ID ...]
		* 从stream删除一条或者多条消息
	
	XTRIM
		* XTRIM key MAXLEN [~] count
		* 把stream中的消息裁剪到指定的数量, 跟XADD中的 MAXLEN 参数作用一样
		
	XLEN
		* XLEN key
		* 获取stream中的消息数量
		
	XRANGE
		* XRANGE key start end [COUNT count]
		* 从stream中根据指定范围条件获取一段范围内的消息
		
	XREVRANGE
		* XREVRANGE key end start [COUNT count]
		* 跟 XRANGE 一样, 不过是反向获取
		
	XREAD
		* XREAD [COUNT count] [BLOCK milliseconds] STREAMS key [key ...] ID [ID ...]
		* 从stream中根据指定id的位置, 阻塞/非阻塞的读取一条/多条消息
		
	XGROUP
		* XGROUP [CREATE key groupname id-or-$] [SETID key id-or-$] [DESTROY key groupname] [DELCONSUMER key groupname consumername]
		* 创建/删除消费组
		
	XREADGROUP
		* XREADGROUP GROUP group consumer [COUNT count] [BLOCK milliseconds] STREAMS key [key ...] ID [ID ...]
		* 分组消费, 从stream中根据group和consumer读取若干未被消费的消息
		
	XPENDING
		* XPENDING key group [start end count] [consumer]
		* 从stream中读取一个group,consumer已经获取但是还未通知消费成功的消息队列
		
	XACK
		* XACK key group ID [ID ...]
		* 通知一条或者多条消息已经被成功的消费, 通知仅仅会从 XPENDING 中删除消息, 不会从stream中删除消息

	XCLAIM
		* XCLAIM key group consumer min-idle-time ID [ID ...] [IDLE ms] [TIME ms-unix-time] [RETRYCOUNT count] [force] [justid]
		* 把一条/多条消息, 从一个consumer的XPENDING列表, 转移到另一个consumer的XPENDING列表
		
	XINFO
		* XINFO [CONSUMERS key groupname] [GROUPS key] [STREAM key] [HELP]
		* 监控命令, 可以检测各种指标, 命令比较复杂
	
------------------------
Stream					|
------------------------
	# redis5的新特性, 类似于一个MQ系统, Stream是Redis的数据类型中最复杂的

	# 添加数据到stream
		XADD [stream] MAXLEN [maxLen] [id] [key] [value] [key] [value]
			stream
				* 指定stream名称
			
			MAXLEN [maxLen]
				* 可选的参数
				* stream中最大存储的条目, 自动驱逐旧的条目
					XADD mystream MAXLEN 2 * value 1
				* 可以使用一个中间参数(~), 表示至少保存n个项目就行(000或者1010或者1030)
				* 通过使用这个参数, 仅当移除整个节点的时候才执行修整, 这使得命令更高效
					XADD mystream MAXLEN ~ 1000 * value 1

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
				* 开始时间戳和结束时间戳
					XRANGE mystream 1518951480106 1518951480107
				* 两个特殊的符号:-,+ 分别表示可能的最小ID和最大ID
				* 如果没有指定seq部分, 在start参数中默认:0, 在end参数中默认: 正无穷大
			
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
	
	# 删除单个元素
		XDEL [stream] ID [ID ...]
			ID [ID ...]
				* 要删除的id, 可以是多个
	
	# 修剪stream的数据
		XTRIM [stream] MAXLEN [maxLen]
			MAXLEN [maxLen]
				* 删除指定stream中的数据, 只留新的 maxLen 条
		

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
				* 对于非阻塞模式来说$参数是无意义的, 因为读取的消息总是为空
				* 阻塞模式下, 如果参数ID指定为$, 那么COUNT参数不起作用, 只有有数据, 就会立即返回, 阻塞结束
					- 在阻塞模式下如果指定的ID不是$, 比如是0，并且stream中有数据可读, 此时阻塞模式可以简单地认为退化成非阻塞模式, COUNT参数起作用

				* 可以一次性监听N个stream的n个id, 只要其中一个有新的数据, 就会返回
					XREAD BLOCK 0 STREAMS mystream foo $ $
					
				* 在集群环境下, 要注意所有的stream的key必须在同一个slot上否则报错
	
				
	# 消费组管理
		XGROUP [CREATE key groupname id-or-$] [SETID key id-or-$] [DESTROY key groupname] [DELCONSUMER key groupname consumername]
			[stream]
				* 指定stream的名称
			
			[group]
				* 指定group的名称
			
			[id]
				* 表示从大于哪条消息开始消费
				* 如果设置为了0, 表示从头开始消费, 设置为 $ , 只会消费在消费组创建以后的消息
			
			[CREATE]
				* 创建一个消费组(目前创建消费组, stream必须存在)
					XGROUP CREATE mystream mygroup $
				
			[SETID]
				* 修改消费组的消费ID(修改消息分组创建的时候指定的ID起始位置)
					XGROUP SETID mystream mygroup $
			
			[DESTROY] 
				* 删除消费组
					XGROUP DESTROY mystream mygroup
			
			[DELCONSUMER]
				* 删除消费者
					XGROUP DELCONSUMER mystream mygroup consumer
		
	
	
	# 消费组的形式监听消费
		XREADGROUP GROUP [group] [consumer] COUNT [count] BLOCK [milliseconds] STREAMS [stream...] ID [id...]
			GROUP [group]
				* 指定消费组的名称
			
			consumer
				* 指定消费组中, 当前消费者的名称
			
			ID [id...]
				* 消费组中的id, 有一个特殊符号 >
				* 这个特殊的ID只在消费者组的上下文中有效, 其意思是: 到目前为止从未传递给其他消费者的消息
				* 如果id设置为了 > 表示, 仅仅消费当前消费组中, 其他消费者未消费的消息, 通过这个特殊id, 达到: 同一个消费组中, 只有一个消费组可以消费消息
				* 这个特殊的ID其实就是 last_delivered_id
				* 当ID不是特殊字符>时, XREADGROUP不再是从消息队列中读取消息, 而是从consumer的pending消息列表中读取历史消息


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
		
	

	# 获取指定消费组中, 待消费的消息
		XPENDING [stream] [group] [start] [end] [count] [consumer]
		
		* 返回数据的详细信息: 
			1) 1) 1526569498055-0			// 消息id
			   2) "Bob"						// 消费者名称
			   3) (integer) 74170458		// 空闲的毫秒值
			   4) (integer) 1				// 消息被传递的次数
			2) 1) 1526569506935-0
			   2) "Bob"
			   3) (integer) 74170458
			   4) (integer) 1
	
	# 改变消息的消费者
		XCLAIM [stream] [greoup] [consumer] [min-idle-time] [ID ...] [IDLE ms] [TIME ms-unix-time] [RETRYCOUNT count] [force] [justid]
			[stream] [greoup] [consumer]
				* 分别指定stream, 消费组, 以及消费组中的消费者(也就是消息认领者)
			
			[justid]
				* 执行成功后仅仅返回消息的id, 不返回消息的详情
	

	
	# stream的相关信息
		XINFO [CONSUMERS key groupname] [GROUPS key] [STREAM key] [HELP]
		
		* 查看stream的信息
			XINFO STREAM [stream]
				 1) "length"
				 2) (integer) 12
				 3) "radix-tree-keys"
				 4) (integer) 1
				 5) "radix-tree-nodes"
				 6) (integer) 2
				 7) "groups"
				 8) (integer) 1
				 9) "last-generated-id"
				10) "1574135225847-0"
				11) "first-entry"
				12) 1) "1574131352064-0"
					2) 1) "sensor-id"
					   2) "1234"
					   3) "temperature"
					   4) "19.8"
				13) "last-entry"
				14) 1) "1574135225847-0"
					2) 1) "name"
					   2) "KevinBlandy"
					   3) "age"
					   4) "23"574135225847-0"
		
		* 查看stream消费组的信息
			XINFO GROUPS [stream]
				1) 1) "name"
				   2) "group-1"
				   3) "consumers"			// 有多少个消费者
				   4) (integer) 1
				   5) "pending"				// 消费者分组本身pending消息列表,
							* 这个消息列表就是该分组下面所有consumer的pending消息列表集合
							
				   6) (integer) 6
				   7) "last-delivered-id"  // group最后消费的那条消息的ID
							* 不同的消费者做到消费的消息不重复的, 主要原因就是last_delivered_id的存在
							* 消费者从队列中获取还未被消费的消息的时候, 都会从last_delivered_id这个位置开始(不包含)
							* 因此只要last_delivered_id这个位置信息个值随着变化, 不同的消费者获取到的消息就不会重复


				   8) "1574135225847-0"
		
		* 查看stream消费组中消费者的信息
			XINFO CONSUMERS [stream] [group]
				1) 1) "name"
				   2) "consumer-1"
				   3) "pending"					// consumer的pending消息列表集合
				   4) (integer) 6			
				   5) "idle"					// 该consumer空闲了多久
				   6) (integer) 2916753
		

		* 帮助
			XINFO HELP
				1) XINFO <subcommand> arg arg ... arg. Subcommands are:
				2) CONSUMERS <key> <groupname>  -- Show consumer groups of group <groupname>.
				3) GROUPS <key>                 -- Show the stream consumer groups.
				4) STREAM <key>                 -- Show information about the stream.
				5) HELP                         -- Print this help.

	
------------------------
Stream - 消费组			|
------------------------
	# 特点
		1, 不同的消费者（通过消费者ID区分）互不影响，相互隔离，它们彼此看不到各自的历史消费消息
		2, 不同的消费者（通过消费者ID区分）只能看到整个消息队列的一部分消息，也就是说一个消费者只消费整个消息队列的一个子集，并且这些子集绝不会出现重合的消息
		2, 所有消费者消费的消息子集的交集就是整个消息队列集合

			
------------------------
Stream	- 队列的遍历	|
------------------------
	# XRANGE 遍历
		1, 使用 XRANGE key - + COUNT count 试图获取count个消息
		2, 如果发现返回的消息数量ret < count，那么说明所有的消息已经被获取，迭代结束，转5
		3, 如果ret==count，那么说明队列中还有元素，记录返回的消息中最后一个消息的ID为last_id，然后继续迭代，使用命令 XRANGE key last_id + COUNT count
		4, 重复步骤2和3
		5, 迭代结束
	
	# XREAD 遍历
		1, 使用 XREAD COUNT count STREAMS key 0 试图获取count个消息
		2, 如果发现返回的消息个数ret<count，那么说明所有的消息已经被读取完毕，迭代结束
		3, 如果发现返回的消息个数ret==count，那么说明队列中还有元素，记录返回的消息中最后一个消息的ID为last_id，然后继续迭代，使用命令 XREAD COUNT count STREAMS key last_id
		4, 重复步骤2和3
		5, 迭代结束



