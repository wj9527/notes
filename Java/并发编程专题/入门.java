
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
	

# 非原子性的64位操作
	* jvm规定,变量的声明赋值,是一个原子操作,但是存在一个例外
	* 非 volatile 类型的64位数值变量(double & long),jvm允许把64位的读和写操作分为两个32位的读和写
	* 当读取一个非 volatile 的 long 时,读写不在一个线程,那么很可能读到某个值的高32位或者低32位
	* 在多线程环境下,使用共享且可变的 long,double 都是有安全问题的,应该加锁或者使用 volatile 关键字声明

	