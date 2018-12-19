--------------------
映射				|
--------------------
	# 其实就是 Map
	# 可以使用链表或者二分搜索树实现
		class Node<K,V>{
			K key;
			V value;
			Node next;
		}
		class Node<K,V>{
			K key;
			V value;
			Node left;
			Node right;
		}
	
	# 基本的方法
		add(k,v);
		v remove(k);
		boolean contains(k);
		v get(k);
		void set(k,v);
		int size();
		boolean isEmpty();

	
--------------------
基于链表实现的Map	|
--------------------
import java.util.function.BiConsumer;

public class LinkedMap<K, V> {
	private class Node {
		private K key;
		private V value;
		private Node next;

		public Node(K key, V value, LinkedMap<K, V>.Node next) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node() {
			this(null, null, null);
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + ", next=" + next + "]";
		}
	}

	private int size;

	private Node dummyHead;

	public LinkedMap() {
		this.dummyHead = new Node();
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean empty() {
		return this.size == 0;
	}

	private Node getNode(K key) {
		Node node = this.dummyHead.next;
		while (node != null) {
			if (node.key.equals(key)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	public boolean contains(K key) {
		return this.getNode(key) != null;
	}

	public V get(K key) {
		Node node = this.getNode(key);
		return node == null ? null : node.value;
	}

	public void add(K key, V value) {
		Node node = this.getNode(key);
		if (node != null) {
			// 覆盖
			node.value = value;
		} else {
			// 直接插入到头节点
			this.dummyHead.next = new Node(key, value, this.dummyHead.next);
			this.size++;
		}
	}

	public void set(K key, V value) {
		Node node = this.getNode(key);
		if (node != null) {
			node.value = value;
		}
		throw new IllegalArgumentException("key \"" + key + "\"not found");
	}

	public V remove(K key) {
		Node pre = this.dummyHead;
		while (pre.next != null) {
			if (pre.next.key.equals(key)) {// 获取到要删除节点的上一个节点
				break;
			}
			pre = pre.next;
		}
		if (pre.next != null) {
			Node delNode = pre.next;
			pre.next = delNode.next;
			this.size--;
			return delNode.value;
		}
		// 遍历到最后也没找到要删除的节点
		throw new IllegalArgumentException("key \"" + key + "\"not found");
	}

	public void forEach(BiConsumer<K, V> consumer) {
		Node node = this.dummyHead;
		while (node.next != null) {
			consumer.accept(node.next.key, node.next.value);
			node = node.next;
		}
	}
}
