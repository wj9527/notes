-------------------------------
CompletableFuture<T>			|
-------------------------------
	# jdk 1.8 �ṩ�Ķ��� Future<V> ��ʵ��
		CompletableFuture<T> implements Future<T>, CompletionStage<T> 

	# ֧���Իص�����ʽȥ����ִ�н��,��������Ҫͨ��������ǰ�߳�����ȡִ�н��

	# ִ����
		* �����������֧�������Զ���� Executor
		* ��������ã���ôʹ��Ĭ�ϵ�



	# ���캯��
		CompletableFuture()
	
	# ��̬��������
		CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
			* ���������У�������������ִ����ϲŻ᷵��

		CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
			* ���������У�ֻҪ��һ��������ɷ��أ���ô�ͻ᷵��

		<U> CompletableFuture<U> completedFuture(U value)
			* ����һ���Ѿ������״̬�� CompletableFuture
		
		<U> CompletionStage<U> completedStage(U value)
		<U> CompletableFuture<U> failedFuture(Throwable ex)
		<U> CompletionStage<U> failedStage(Throwable ex)

		
		CompletableFuture<Void> runAsync(Runnable runnable)
		CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
			* �첽ִ��ĳ�����񣬷������� CompletableFuture
			* ����Ҫ����ֵ
		
		<U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
		<U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
			* �첽ִ��ĳ�����񣬷������� CompletableFuture
			* ��Ҫ����ֵ

		Executor delayedExecutor(long delay, TimeUnit unit, Executor executor)
		Executor delayedExecutor(long delay, TimeUnit unit)
	
	# ʵ������
		CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
		CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
		CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action,Executor executor)
		CompletableFuture<Void> runAfterEither(CompletionStage<?> other,Runnable action)
		<U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)
		<U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn)
		<U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn,Executor executor)

		boolean cancel(boolean mayInterruptIfRunning)
		boolean (T value)
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
		
		
		CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit)
		CompletableFuture<T> orTimeout(long timeout, TimeUnit unit)
		CompletableFuture<T> completeAsync(Supplier<? extends T> supplier)
		CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor)
		CompletionStage<T> minimalCompletionStage()
		CompletableFuture<T> copy()
		Executor defaultExecutor()
		<U> CompletableFuture<U> newIncompleteFuture()

		
		* û��ָ�� Executor �ķ�����ʹ�� ForkJoinPool.commonPool() ��Ϊ�����̳߳�ִ���첽����
		* ���ָ���̳߳أ���ʹ��ָ�����̳߳�����

		* runAsync������֧�ַ���ֵ
		* supplyAsync����֧�ַ���ֵ

		* whenComplete, ��ִ�е�ǰ������߳�ִ�м���ִ�� whenComplete ������
		* whenCompleteAsync, ��ִ�а� whenCompleteAsync �����������ύ���̳߳�������ִ��

		* ��һ���߳�������һ���߳�ʱ,����ʹ�� thenApply ���������������̴߳��л�

		* handle ��ִ���������ʱ�Խ���Ĵ���
		* handle ������ thenApply ��������ʽ����һ��
		* ��ͬ���� handle ����������ɺ���ִ��, �����Դ����쳣������,thenApply ֻ����ִ������������,��������쳣��ִ�� thenApply ����

		* thenCombine ��� ���� CompletionStage ������ִ����ɺ�,����������Ľ��һ�齻�� thenCombine ������
		* ������CompletionStage��ִ����ɺ�, �ѽ��һ�齻��thenAcceptBoth����������

		* ����CompletionStage, ˭ִ�з��صĽ����, �����Ǹ�CompletionStage�Ľ��������һ����ת������

	


-------------------------------
CompletableFuture<T>			|
-------------------------------

		CompletableFuture.supplyAsync(() -> 1)		// ��һ������,�̷߳��� 1
				.thenApply(i -> i + 1)				// �ڶ�������,ʹ�õ�һ������ķ���ֵ��Ϊ����
				.thenApply(i -> i * 2)				// ����������,ʹ�õڶ�������ķ���ֵ��Ϊ����
				.whenComplete((r, e) -> {
					System.out.println(r); //  ���ִ�������,��ȡ��ִ�еĽ��
				});
		
-------------------------------
Demo1
-------------------------------

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
	
	public static String func (String value) {
		try {
			Thread.sleep(1000);			// ģ���ʱ����
		} catch (InterruptedException e) {
			
		}
		return value + ":" + value;
	}
	
	public static Future<String> funcAsync (String value) {
		CompletableFuture<String> completableFuture = new CompletableFuture<String>();
		new Thread(() -> {
			try {
				String retVal = func(value);
				completableFuture.complete(retVal); // ������ɣ����ý��
			} catch (Exception e) {
				completableFuture.completeExceptionally(e); // �����쳣�������쳣
			}
		}).start();
		return completableFuture;
	}
	
	public static void main(String[] args) {
		System.out.println("��ʼ���÷���....");
		Future<String> future = funcAsync("Hello");
		System.out.println("�����������....");
		System.out.println("�ȴ���ȡ����ֵ...");
		try {
			System.out.println(future.get());  // �������ȵȳ���ִ�����
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}