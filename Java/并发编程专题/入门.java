
# 重入
	* Foo线程已经获取了a锁,Foo再次获取a锁,是OK可以成功获取到的,这个就是重入
		class Bar{
			public synchronized void test() {
			}
		}
		public class Foo extends Bar{
			public synchronized void test() {
				/*
					当前线程已经持有了this这把锁,访问父类的方法,如果没有重入机制,那么就会一直阻塞.因为this锁已经被持有了

				*/
				super.test();		
			}
		}
	
	* 重入的实现
		1,为这个锁设置了一个计数器,当有一个线程持有了这个锁的时候,计数器 +1
		2,当线程退出同步代码块的时候,计数器 -1,当计数器为0的时候,这个锁将会被释放
	
