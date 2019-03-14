# 时间轮算法
	*  定时器大概有两种,一种是开阻塞线程,另一种是开一个任务队列然后定期扫描
	*  显而易见这两种方式的弊端很明显,前者对线程消耗过大,后者对时间消耗过大(很多未到时间的任务会被多次重复扫描消耗性能)

# 时间轮算法是基于循环链表数据结构

# 几个关键的概念
	ticksPerWheel	
		* 一轮的tick数

	tickDuration
		* 一个tick的持续时间
		* timeUnit
	



import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TimingWheel<E> {
	// 一个tick的持续时间
	private final long tickDuration;
	// tick数量
	private final int ticksPerWheel;
	// 当前tick的指针
	private volatile int currentTickIndex = 0;

	// 过期的任务
	private final CopyOnWriteArrayList<ExpirationListener<E>> expirationListeners = new CopyOnWriteArrayList<ExpirationListener<E>>();
	
	// 刻度插槽
	private final ArrayList<Slot<E>> wheel;
	
	// 指示符
	private final Map<E, Slot<E>> indicator = new ConcurrentHashMap<E, Slot<E>>();

	// 是否shutdown
	private final AtomicBoolean shutdown = new AtomicBoolean(false);
	
	// 锁
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	// 工作线程
	private Thread workerThread;

	/**
	 * @param tickDuration 每一刻度毫秒
	 * @param ticksPerWheel 刻度多少
	 * @param timeUnit 单位
	 */
	public TimingWheel(int tickDuration, int ticksPerWheel, TimeUnit timeUnit) {
		if (timeUnit == null) {
			throw new NullPointerException("unit");
		}
		if (tickDuration <= 0) {
			throw new IllegalArgumentException("tickDuration must be greater than 0: " + tickDuration);
		}
		if (ticksPerWheel <= 0) {
			throw new IllegalArgumentException("ticksPerWheel must be greater than 0: " + ticksPerWheel);
		}
		
		// 实例化插槽
		this.wheel = new ArrayList<Slot<E>>();
		this.tickDuration = TimeUnit.MILLISECONDS.convert(tickDuration, timeUnit);
		this.ticksPerWheel = ticksPerWheel + 1;

		for (int i = 0; i < this.ticksPerWheel; i++) {
			// 添加插槽里面的对象
			wheel.add(new Slot<E>(i));
		}
		
		wheel.trimToSize();

		// 实例化工作线程
		workerThread = new Thread(new TickWorker(), "Timing-Wheel");
	}

	public void start() {
		if (shutdown.get()) {
			throw new IllegalStateException("Cannot be started once stopped");
		}

		if (!workerThread.isAlive()) {
			// 启动工作线程
			workerThread.start();
		}
	}

	public boolean stop() {
		if (!shutdown.compareAndSet(false, true)) {
			return false;
		}

		boolean interrupted = false;
		while (workerThread.isAlive()) {
			workerThread.interrupt();
			try {
				workerThread.join(100);
			} catch (InterruptedException e) {
				interrupted = true;
			}
		}
		if (interrupted) {
			Thread.currentThread().interrupt();
		}

		return true;
	}

	public void addExpirationListener(ExpirationListener<E> listener) {
		expirationListeners.add(listener);
	}

	public void removeExpirationListener(ExpirationListener<E> listener) {
		expirationListeners.remove(listener);
	}

	public long add(E e) {
		synchronized (e) {
			checkAdd(e);
			int previousTickIndex = getPreviousTickIndex();
			Slot<E> slot = wheel.get(previousTickIndex);
			slot.add(e);
			indicator.put(e, slot);
			return (ticksPerWheel - 1) * tickDuration;
		}
	}

	private void checkAdd(E e) {
		Slot<E> slot = indicator.get(e);
		if (slot != null) {
			slot.remove(e);
		}
	}

	private int getPreviousTickIndex() {
		lock.readLock().lock();
		try {
			int cti = currentTickIndex;
			if (cti == 0) {
				return ticksPerWheel - 1;
			}

			return cti - 1;
		} finally {
			lock.readLock().unlock();
		}
	}

	public boolean remove(E e) {
		synchronized (e) {
			Slot<E> slot = indicator.get(e);
			if (slot == null) {
				return false;
			}

			indicator.remove(e);
			return slot.remove(e) != null;
		}
	}

	private void notifyExpired(int idx) {
		
		// 获取当前插槽的Slot
		Slot<E> slot = wheel.get(idx);
		
		// 获取当期Slot中的任务Set
		Set<E> elements = slot.elements();
		
		for (E e : elements) {
			// 从Slot中移除任务
			slot.remove(e);
			synchronized (e) {
				// 从指示符获取到任务对象
				Slot<E> latestSlot = indicator.get(e);
				if (latestSlot.equals(slot)) {
					indicator.remove(e);
				}
			}
			for (ExpirationListener<E> listener : expirationListeners) {
				// 触发过期事件
				listener.expired(e);
			}
		}
	}

	private class TickWorker implements Runnable {

		private long startTime;
		
		private long tick;

		@Override
		public void run() {
			// 时间轮开始时间
			startTime = System.currentTimeMillis();
			tick = 1;
			for (int i = 0; !shutdown.get(); i++) {
				if (i == wheel.size()) {
					// 重置指针
					i = 0;
				}
				lock.writeLock().lock();
				try {
					// 同步设置指针位置
					currentTickIndex = i;
				} finally {
					lock.writeLock().unlock();
				}
				
				// 唤醒到期任务
				notifyExpired(currentTickIndex);
				
				waitForNextTick();
			}
		}
		
		// 移动到下一个刻度
		private void waitForNextTick() {
			for (;;) {
				long currentTime = System.currentTimeMillis();
				// 线程暂停时间 = 每个刻度耗时 * 刻度 - (开始时间 - 现在时间)
				long sleepTime = tickDuration * tick - (currentTime - startTime);

				if (sleepTime <= 0) {
					break;
				}

				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					return;
				}
			}

			tick++;
		}
	}

	private static class Slot<E> {

		private int id;
		private Map<E, E> elements = new ConcurrentHashMap<E, E>();

		public Slot(int id) {
			this.id = id;
		}

		public void add(E e) {
			elements.put(e, e);
		}

		public E remove(E e) {
			return elements.remove(e);
		}

		public Set<E> elements() {
			return elements.keySet();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("rawtypes")
			Slot other = (Slot) obj;
			if (id != other.id)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Slot [id=" + id + ", elements=" + elements + "]";
		}
	}
}

public interface ExpirationListener<E> {
	
	void expired(E expiredObject);
}
