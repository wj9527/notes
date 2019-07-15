------------------------
单向环形链表			|
------------------------
	# 实现思路
		* 先创建第一个节点，让first指向该节点，形成环形
	
	# 遍历的思路
		
	# 约瑟夫环问题
		* 设置编号为: 1, 2, 3...n个人围绕作一圈儿
		* 约定编号为k的人从1开始报数，数到m位置的人出列(1 <= k <= n)
		* m的下一位又从1开始报数，数到m的那个人又出列
		* 依次类推，直到所有的人都出列为止，由此产生一个出队编号的序列
	
	

------------------------
Python 实现				|
------------------------
	class Node(object):
		def __init__(self, value, next):
			self.value = value
			self.nex = next


	class Joseph(object):
		# 初始化
		def __init__(self):
			self.first = None
			self.current = None

		# 添加节点
		def add(self, value):
			node = Node(value, None)
			if self.first is None:
				self.first = node
				self.first.next = self.first
				self.current = self.first
			else:
				self.current.next = node
				node.next = self.first
				self.current = node

		# 遍历节点
		def forEach(self, handler):
			if self.first is None:
				return
			temp = self.first
			while True:
				handler(temp.value)
				# 遍历完毕
				if temp.next == self.first:
					break
				temp = temp.next


	queue = Joseph()

	queue.add(1)
	queue.add(2)
	queue.add(3)
	queue.add(4)
	queue.add(5)
	queue.add(6)
	queue.add(7)


	queue.forEach(lambda x:print(x))





