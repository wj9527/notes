--------------------
Executors			|
--------------------
	# 创建各种线程池的工厂类

	ExecutorService newFixedThreadPool(2);		
		* 创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。
	
	ExecutorService newCachedThreadPool();
		* 创建一个可根据需要创建新线程的线程池,但是在以前构造的线程可用时将重用它们。
		* 对于执行很多短期异步任务的程序而言,这些线程池通常可提高程序性能。
		* 线程数量不固定,可以根据需求更新线程数量

	ExecutorService newSingleThreadExecutor()
		* 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
		* 里面只有一个线程
	
	ExecutorService newScheduledThreadPool(10);
		* 创建调度线程池
		* 创建固定大小的线程池,可以延时/重复的执行任务调度
	
