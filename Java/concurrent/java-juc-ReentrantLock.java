------------------------
ReentrantLock			|
------------------------
	# 可重入锁
	
	# 实例方法
		lock();
		lockInterruptibly();
		newCondition();
		unlock();

		getHoldCount();
		getQueueLength();
		getWaitQueueLength(null);

		hasQueuedThread(null);
		hasWaiters(null);
		hasQueuedThreads();

		isFair();
		isHeldByCurrentThread();
		isLocked();

		tryLock();
		tryLock(0, null);