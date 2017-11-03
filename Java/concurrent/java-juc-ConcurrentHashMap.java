----------------------------
ConcurrentHashMap			|
----------------------------
	# 锁分段机制
		* 默认有 16 个段(Segment)
		* 每个段中有 16 个元素
	
	# JDK1.8后,ConcurrentHashMap 底层也采用了 CAS 算法

	# 几个 Map 之间的比较
		* Hashtable 线程安全,但是锁了整张表,效率低
		* HashMap 线程不安全,效率高
		* ConcurrentHashMap 线程安全,采用锁分段机制,效率较高
	
