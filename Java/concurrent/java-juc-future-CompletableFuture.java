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
		CompletableFuture<Void> thenAccept(Consumer<? super T> action)
		CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
		CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor)
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
		CompletableFuture<T> toCompletableFuture()

		CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
		CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action)
		CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor)









