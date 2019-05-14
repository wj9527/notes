-------------------------------
CompletableFuture<T>			|
-------------------------------
	# jdk 1.8 提供的对于 Future<V> 的实现
		CompletableFuture<T> implements Future<T>, CompletionStage<T> 

	# 支持以回调的形式去处理执行结果,而不用需要通过阻塞当前线程来获取执行结果

	# 构造函数
		CompletableFuture()
	
	# 静态工厂函数
		CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
		CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
		<U> CompletableFuture<U> completedFuture(U value)
		
		CompletableFuture<Void> runAsync(Runnable runnable)
		CompletableFuture<Void> runAsync(Runnable runnable,Executor executor)
		
		<U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
		<U> CompletableFuture<U> supplyAsync(Supplier<U> supplier,Executor executor)
	
	# 实例方法
		CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
		CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
		CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action,Executor executor)
		CompletableFuture<Void> runAfterEither(CompletionStage<?> other,Runnable action)
		<U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)
		<U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn)
		<U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn,Executor executor)

		boolean cancel(boolean mayInterruptIfRunning)
		boolean complete(T value)
		boolean completeExceptionally(Throwable ex)
		CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
		T get()
		T get(long timeout, TimeUnit unit)
		T getNow(T valueIfAbsent)
		int getNumberOfDependents()
		<U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) 
		<U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn)
		<U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor)

		boolean isCancelled()
		boolean isCompletedExceptionally()

		boolean isDone()
		T join()
		void obtrudeException(Throwable ex)
		void obtrudeValue(T value)

		CompletableFuture<Void> runAfterBoth(CompletionStage<?> other,Runnable action)
		CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action)
		CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action,Executor executor)
		CompletableFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action)
		CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action)
		CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action, Executor executor)
		
		<U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action)
		<U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action)
		<U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action, Executor executor)
		<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
		<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
		<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
		<U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn)
		<U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn)
		<U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn, Executor executor)
		<U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)
		<U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) 
		<U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn,Executor executor)

		CompletableFuture<Void> thenRun(Runnable action)
		CompletableFuture<Void> thenRunAsync(Runnable action)
		CompletableFuture<Void> thenRunAsync(Runnable action,Executor executor)

		CompletableFuture<Void> thenAccept(Consumer<? super T> action)
		CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
		CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor)

		<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
		<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
		<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)


		CompletableFuture<T> toCompletableFuture()

		CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
		CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action)
		CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor)


		
		* 没有指定 Executor 的方法会使用 ForkJoinPool.commonPool() 作为它的线程池执行异步代码
		* 如果指定线程池，则使用指定的线程池运行

		* runAsync方法不支持返回值
		* supplyAsync可以支持返回值

		* whenComplete, 是执行当前任务的线程执行继续执行 whenComplete 的任务
		* whenCompleteAsync, 是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行

		* 当一个线程依赖另一个线程时,可以使用 thenApply 方法来把这两个线程串行化

		* handle 是执行任务完成时对结果的处理
		* handle 方法和 thenApply 方法处理方式基本一样
		* 不同的是 handle 是在任务完成后再执行, 还可以处理异常的任务,thenApply 只可以执行正常的任务,任务出现异常则不执行 thenApply 方法

		* thenCombine 会把 两个 CompletionStage 的任务都执行完成后,把两个任务的结果一块交给 thenCombine 来处理
		* 当两个CompletionStage都执行完成后, 把结果一块交给thenAcceptBoth来进行消耗

		* 两个CompletionStage, 谁执行返回的结果快, 就用那个CompletionStage的结果进行下一步的转化操作

	


-------------------------------
CompletableFuture<T>			|
-------------------------------

		CompletableFuture.supplyAsync(() -> 1)		// 第一个任务,线程返回 1
				.thenApply(i -> i + 1)				// 第二个任务,使用第一个任务的返回值作为参数
				.thenApply(i -> i * 2)				// 第三个任务,使用第二个任务的返回值作为参数
				.whenComplete((r, e) -> {
					System.out.println(r); //  最后都执行完毕了,获取到执行的结果
				});
		
