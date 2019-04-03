--------------------
HashMap				|
--------------------
	# JDK 1.7及以前使用 Hash 表 + 链表实现
	# JDK 1.8及以后使用 Hash 表 + 红黑树实现(优化了查询效率)
		* 当触发了一定的条件后,会把链表转换为红黑树
	
	# Hash表的尺寸和容量非常的重要
		* 一般来说,Hash表这个容器当有数据要插入时,都会检查容量有没有超过设定的thredhold,如果超过,需要增大hash表的尺寸
		* 但是这样一来,整个hash表里的无素都需要被重算一遍,这叫rehash,这个成本相当的大
	
	# 核心的成员变量
		static final int TREEIFY_THRESHOLD = 8;
			* 用于判断是否需要将链表转换为红黑树的阈值
			* 如果链表的长度大于了该值,大于了该值就会转换为红黑树
		
		int size;
			* 存储的数据数量

		int modCount;
			* 修改的次数

		float loadFactor;
			* 负载因子,决定了什么时候会触发扩容
				容器大小 x 负载因子 = 触发扩容的大小
		
		int threshold;
			* 调整Map大小的下一个值

		Node<K,V>[] table;
			* 存放数据的数组

		Set<Map.Entry<K,V>> entrySet;
	
	# HashMap 在并发场景下使用时容易出现问题 
		* 多线程put操作后,get操作导致死循环
			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < 1000; i++) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						map.put(UUID.randomUUID().toString(), "");
					}
				}).start();
			}

			* HashMap 扩容的时候会调用 resize() 方法
			* 这里的并发操作容易在一个桶上形成环形链表
				* 当获取一个不存在的 key 时,计算出的 index 正好是环形链表的下标就会出现死循环

		
		* 多线程put非null元素后,get操作得到null值(导致元素丢失)
			

		
	
