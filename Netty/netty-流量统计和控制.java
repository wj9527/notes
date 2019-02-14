----------------------------
流量统计					|
----------------------------
	# 类库
		TrafficCounter
			* 统计的数据结果

		AbstractTrafficShapingHandler
			* 各种类型的统计插件

			|-ChannelTrafficShapingHandler
			|-GlobalChannelTrafficShapingHandler
			|-GlobalTrafficShapingHandler
	
	# AbstractTrafficShapingHandler
		* 
	

----------------------------
ChannelTrafficShapingHandler|
----------------------------
	# 单个 Channel 级别的统计
		ChannelTrafficShapingHandler(long checkInterval)
		ChannelTrafficShapingHandler(long writeLimit,long readLimit)
		ChannelTrafficShapingHandler(long writeLimit,long readLimit, long checkInterval)
		ChannelTrafficShapingHandler(long writeLimit, long readLimit,long checkInterval, long maxTime)
	


-----------------------------------
GlobalChannelTrafficShapingHandler |
-----------------------------------
	# 可以统计多条 Channel 
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor)
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor, long checkInterval)
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor, long writeGlobalLimit, long readGlobalLimit, long writeChannelLimit, long readChannelLimit)
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor,long writeGlobalLimit, long readGlobalLimit,long writeChannelLimit, long readChannelLimit, long checkInterval)
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor,long writeGlobalLimit, long readGlobalLimit,long writeChannelLimit, long readChannelLimit, long checkInterval, long maxTime)


-----------------------------------
GlobalTrafficShapingHandler			|
-----------------------------------
	# 全局级别的
		GlobalTrafficShapingHandler(EventExecutor executor)
		GlobalTrafficShapingHandler(ScheduledExecutorService executor, long checkInterval)
		GlobalTrafficShapingHandler(ScheduledExecutorService executor, long writeLimit,long readLimit)
		GlobalTrafficShapingHandler(ScheduledExecutorService executor, long writeLimit,long readLimit, long checkInterval)
		GlobalTrafficShapingHandler(ScheduledExecutorService executor, long writeLimit, long readLimit,long checkInterval, long maxTime)
