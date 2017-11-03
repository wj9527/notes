----------------------------
Duration					|
----------------------------
	# 用于计算两个时间之间的间隔
	# 构造(使用Duration静态方法)
		Duration between(Instant instant1,Instant instant2);
			* 计算两个时间戳之间的间隔
		
		Duration between(LocalTtime localTtime1,LocalTtime localTtime2);
			* 计算两个时间之间的间隔
	
----------------------------
Duration-API				|
----------------------------
	long toMillis();
		* 返回毫秒值
	