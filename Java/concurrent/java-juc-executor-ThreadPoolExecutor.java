---------------------------
ThreadPoolExecutor			|
---------------------------
	# 线程池的实现
	# 构造方法
		ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue)
		ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,RejectedExecutionHandler handler)
		ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory)
		ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler)

		* corePoolSize
			* 线程池基本的线程数量
		* maximumPoolSize
			* 线程池最大线程数量
		* keepAliveTime
			* 线程空闲后的存活时间
		* unit
			*  存活时间的单位
		* workQueue
			* 存放任务的阻塞队列
		* threadFactory
			* 线程池工厂类
		* handler
			* 在线程池无法处理新任务时的处理handler
		
	# 实例方法
		void allowCoreThreadTimeOut(boolean value)
		boolean allowsCoreThreadTimeOut()
		int getCorePoolSize()
		boolean awaitTermination(long timeout, TimeUnit unit)

		
		int getActiveCount()
		long getCompletedTaskCount()
		long getKeepAliveTime(TimeUnit unit)
		int getLargestPoolSize()
		int getMaximumPoolSize()
		int getPoolSize()
		BlockingQueue<Runnable> getQueue()
		RejectedExecutionHandler getRejectedExecutionHandler()
		long getTaskCount()
		ThreadFactory getThreadFactory()
		boolean isTerminated()
			* 如果队列中的所有任务都处理完毕后返回 true

		boolean isTerminating() 
		int prestartAllCoreThreads()
		boolean prestartCoreThread()
		void purge()
		boolean remove(Runnable task)
		void setCorePoolSize(int corePoolSize)
		void setKeepAliveTime(long time, TimeUnit unit)
		void setMaximumPoolSize(int maximumPoolSize)
		void setRejectedExecutionHandler(RejectedExecutionHandler handler) 
		void setThreadFactory(ThreadFactory threadFactory)

		boolean isShutdown()
		void shutdown()
			* 会等到所有任务完成才会关闭
		List<Runnable> shutdownNow()
			* 立即关闭线程池

		void execute(Runnable command)
		Future<?> submit(Runnable task)
			* 执行任务,submit可以获取到执行结果的返回值和抛出的异常

		<T> Future<T> submit(Runnable task, T result)
		<T> Future<T> submit(Callable<T> task)
		<T> T invokeAny(Collection<? extends Callable<T>> tasks,long timeout, TimeUnit unit)
		<T> T invokeAny(Collection<? extends Callable<T>> tasks)
		<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,long timeout, TimeUnit unit)
		<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
	
	# 内部类
		* 他们都是 ThreadPoolExecutor 的实现类,负责处理线程池无法执行新任务时的情况

		AbortPolicy
			* 无法处理时抛出异常
			* 源码
				public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
					throw new RejectedExecutionException("Task " + r.toString() + " rejected from " +  e.toString());
		        }

		CallerRunsPolicy
			* 直接运行新任务
			* 源码
				public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
					if (!e.isShutdown()) {
						r.run();
					}
				}

		DiscardOldestPolicy
			* 丢弃队列中最老的任务
			* 源码
				public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
					if (!e.isShutdown()) {
						e.getQueue().poll();
						e.execute(r);
					}
				}

		DiscardPolicy
			* 丢弃新任务
			* 源码
				public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
				}

---------------------------
RejectedExecutionHandler   |
---------------------------
	# ThreadPoolExecutor 无法处理新任务时的处理Handler 接口
	# 抽象方法
		void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
