----------------------------
CountDownLatch				|
----------------------------
	# 在完成某些运算的时候,只有其他的运算全部完成,当前运算才会执行
	# 创建
		CountDownLatch countDownLatch = new CountDownLatch(5);
			* 创建的时候,就指定一个基数
	# 方法
		countDownLatch.countDown();
			* 在基数上减1,当值为0的时候,countDownLatch
		
		await();
			

	
