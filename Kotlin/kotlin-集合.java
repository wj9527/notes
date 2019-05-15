-----------------
集合			 | 
-----------------
	# 创建集合的函数
		var list = arrayListOf(1,2,3,4);						ArrayList

		var set = hashSetOf(1,2,3,4);							HashSet

		var map = hashMapOf(1 to "1", 2 to "2", 3 to "3")		HashMap
			* 使用 to 关键字,完成 key 和 value的映射
	
		var list = listOf(1,2,3,4)								java.util.Arrays$ArrayList
			* Arrasys.asList()
	
	# list/set 支持过滤函数
		last();
		min();
		max();