------------------------
二分搜索树				|
------------------------
	# 二分搜索树是二叉树
	# 二分搜索树的每个节点值 
		* 都要大于左子树的所有节点值
		* 都要小余右子树的所有节点值
		* 不能重复

	# 存储在树中的数据需要可排序
	
	# 二分搜索树添加元素的非递归写法,跟链表很像
		
	# 包含重复元素的树
		* 左子树小余等于节点,或者右子树大于等于节点

	// 这种递归，add调用之前需要判断root节点是否为null
	private void addRecursion(Node node, E value) {
		int result = node.value.compareTo(value);
		if (result < 0) {
			// 左树
			if (node.left == null) {
				node.left = new Node(value);
			} else {
				this.addRecursion(node.left, value);
			}
		} else if (result > 0) {
			// 右树
			if (node.right == null) {
				node.right = new Node(value);
			} else {
				this.addRecursion(node.right, value);
			}
		}
		// 相等
		return;
	}