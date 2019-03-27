------------------------
日志存储				|
------------------------

------------------------
文件目录的布局			|
------------------------
	# 主题逻辑布局
		Topic
			|-Parition-1
				|-Log
					|-LogSgement
						|-.log			日志文件
						|-.index		偏移量索引文件
						|-.timeindex	时间戳索引文件
						|-.txnindex		事务索引文件
						|-.delete
						|-.cleaned
						|-.swap
						|-.snapshot
						|-leader-epoch-checkpoint
						|-other			其他文件
			|-Parition-x
	
		* log以文件夹的形式存在于磁盘上
		* 每个 LogSgement 对应磁盘上的一个日志文件(log)和两个索引文件(index,timeindex)
	
	# 主题物理布局
		Kafka-Log-Dir
			topic-log-0
				00000000000000000000.index
				00000000000000000000.log
				00000000000000000000.timeindex

				00000000000000001111.index
				00000000000000001111.log
				00000000000000001111.timeindex

				00000000000000002222.index
				00000000000000002222.log
				00000000000000002222.timeindex
			
		* 文件夹命名方式为:<主题名称>-<分区号>
			topic-log-0 ,表示 topic-log 主题的 第一个分区 0
		
		* 具有相同名称的,一个日志文件(log)和两个索引文件(index,timeindex)组成了一个LogSgement
		* 往LogSgement中写入数据,到达了一定量后,就会创建新LogSgement
		* 文件名称的数字表示当前LogSgement的基准偏移量(从哪里开始的),由20个数字长度组成,不足前面补充0
			00000000000000000000		第一个Segemnt全是0,表示当前Segement是从0开始写入数据的
			00000000000000000133		第二个Sgement以133结尾,表示当前Segment是从133开始写入数据的,也就是说上一个Segement中有 133 条消息(0 - 132)


	# 日志文件目录
		* broker的配置项 log.dirs 可以配置一个或者多个日志目录
		* 每个日志目录下都有几个文件
			log-start-offset-checkpoint
			recovery-point-offset-checkpoint
			replication-offset-checkpoint
			cleaner-offset-checkpoint
			meta.propertiesrecovery-point-offset-checkpoint

		* 当创建主题的时候,系统会选择分区最少的那个目录来创建
	

