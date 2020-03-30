--------------------
HashMap				|
--------------------
	# 存储结构的变化
		* JDK 1.7及以前使用 Hash 表 + 链表实现
		* JDK 1.8及以后使用 Hash 表 + 红黑树实现(优化了查询效率)
		* 当触发了一定的条件后,会把链表转换为红黑树
	
	# 插入节点的变化
		* JDK1.7用的是头插法, 而JDK1.8及之后使用的都是尾插法
		* 因为JDK1.7是用单链表进行的纵向延伸, 当采用头插法就是能够提高插入的效率, 但是也会容易出现逆序且环形链表死循环问题
		* 是在JDK1.8之后是因为加入了红黑树使用尾插法, 能够避免出现逆序且链表死循环的问题
	
	# Hash表的尺寸和容量非常的重要
		* 一般来说,Hash表这个容器当有数据要插入时,都会检查容量有没有超过设定的 thredhold,如果超过,需要增大hash表的尺寸
		* 但是这样一来,整个hash表里的无素都需要被重算一遍,这叫rehash,这个成本相当的大
	
	# 底层是数组 + 单向链表(红黑树)
		Node<K,V>[] table;
		class Node {
			final int hash;
			final K key;
			V value;
			Node<K,V> next;
		}

	# 核心的属性
		static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
			* 默认的存储桶数(大小)

		static final int MAXIMUM_CAPACITY = 1 << 30;
			* 最大的存储桶数量

		static final float DEFAULT_LOAD_FACTOR = 0.75f;
			* 默认的负载因子, 决定什么时候扩容
				容器大小 x 负载因子 = 触发扩容的大小

		static final int TREEIFY_THRESHOLD = 8;
			* 用于判断是否需要将链表转换为红黑树的阈值
			* 如果链表的长度大于了该值,大于了该值就会转换为红黑树

		static final int UNTREEIFY_THRESHOLD = 6;
			* 把红黑树转换为链表的阈值

		static final int MIN_TREEIFY_CAPACITY = 64;

		transient Node<K,V>[] table;
		transient Set<Map.Entry<K,V>> entrySet;
		int size;
			* 存储的数据数量

		int modCount;
			* 修改的次数

		float loadFactor;
			* 负载因子
		
		int threshold;
			* 调整Map大小的下一个值

	
	# HashMap 在并发场景下使用时容易出现问题 
		* 多线程put操作后,get操作导致死循环(据说jdk8修复了这个问题)
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

		
		* 多线程put非null元素后,get操作得到null值(导致元素丢失,这个问题jdk8没有修复)
		
	

	