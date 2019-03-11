-------------------------------
Future<V>						|
-------------------------------
	# 异步执行结果的接口
	# 方法
		boolean cancel(boolean mayInterruptIfRunning);
			* 取消执行
			* mayInterruptIfRunning 设置是否要抛出线程中断异常

		boolean isCancelled();
			* 是否取消执行

		boolean isDone();
			* 是否执行完毕

		V get() throws InterruptedException, ExecutionException;
			* 获取到执行的结果,会阻塞当前的线程

		V get(long timeout, TimeUnit unit)throws InterruptedException, ExecutionException, TimeoutException;
			* 获取到执行的结果,会阻塞当前的线程
			* 可以设置一个超时时间,超时后抛出 TimeoutException

-------------------------------
CompletableFuture<T>			|
-------------------------------
	# jdk 1.8 提供的对于 Future<V> 的实现
		CompletableFuture<T> implements Future<T>, CompletionStage<T> 

	# 支持以回调的形式去处理执行结果,而不用需要通过阻塞当前线程来获取执行结果

