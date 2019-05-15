-----------------
集合			 | 
-----------------
	# 创建集合的函数
		var list = listOf(1,2,3,4)								java.util.Arrays$ArrayList
			* Arrasys.asList()

		var list = arrayListOf(1,2,3,4);						ArrayList

		var set = hashSetOf(1,2,3,4);							HashSet
	
		<A,B> mapOf(pairs:Pair<A,B>)								

		var map = hashMapOf(1 to "1", 2 to "2", 3 to "3")		HashMap
			* 使用 to 关键字,完成 key 和 value的映射
	
	# list/set 支持的一些操作(扩展函数)
		last();
		min();
		max();
	
	
	# 其他的一些相关函数
		var pari = Pair(v1,v2)
			* 返回一个 pair 对象,一般用于构造 Map 的一个映射
			* 这个对象就俩属性(都是泛型)
				public val first: A
				public val second: B
			
			* 该对象可以被解构赋值
				var (key, value) = Pair("name", "KevinBlandy")
				println("key=$key, value=$value")