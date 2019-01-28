----------------------------
CountDownLatch				|
----------------------------
	# 在完成某些运算的时候,只有其他的运算全部完成,当前运算才会执行
	# 创建
		CountDownLatch countDownLatch = new CountDownLatch(5);
			* 创建的时候,就指定一个基数
	# 方法
		countDown();
			* 在基数上减1,当值为0的时候,countDownLatch
		
		await();
			* 阻塞,等待其他线程执行完,直到基数 == 0
			* 其实本身就是个循环检测
		
		boolean await(long timeout, TimeUnit unit)
			* 设置超时时间
		
		long getCount()
			* 返回count

	# demo
		
		CountDownLatch countDownLatch = new CountDownLatch(5);

		//启动多线程执行,当有线程执行完毕后,执行该api
		countDownLatch.countDown();

		
		
		//主线程阻塞,直到 countDownLatch 基数 == 0
		countDownLatch.await();