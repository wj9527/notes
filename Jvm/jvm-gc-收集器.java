------------------------
Gc收集器				|
------------------------
	# 收集算法是内存回收的方法论, 收集器则是实现了
	# jvm规范对收集器怎么去实现, 没有任何规定, 所以不同厂家, 不同版本的可能不一样

	# GC收集器目前主要的有
		Serial
		ParNew
		Parallel Scavenge
	
		Garbage First(G1)
		Concurrent Mark Sweep(CMS)
		Parallel Old
		Serial Old(MSC)

		* 不同的收集器可以共存, 组合使用
		* 它们之间没有绝对的最完美的收集器,(如果有, 也不用实现那么多出来)
	

	
