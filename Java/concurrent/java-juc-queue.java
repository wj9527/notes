------------------------------------
Queue								|
------------------------------------
	# 体系
		Queue(java.util)
			|-Deque
				|-ArrayDeque
				|-ConcurrentLinkedDeque
			|-BlockingQueue
			|-AbstractQueue
				|-PriorityQueue
				|-DelayQueue
				|-SynchronousQueue
				|-PriorityBlockingQueue
				|-LinkedTransferQueue
				|-LinkedBlockingDeque
				|-ArrayBlockingQueue
					* 才用数组结构实现
					* 入队出队采用一把锁,导致入队出队相互阻塞,效率低下

				|-LinkedBlockingQueue
					* 采用单链表的形式实现
					* 采用两把锁的锁分离技术实现入队出队互不阻塞
					* 是有界队列,不传入容量时默认为最大int值

				