-----------------
集合			 | 
-----------------
	# 创建集合的函数
		var list = listOf(1,2,3,4)								java.util.Arrays$ArrayList
			* Arrasys.asList()

		var list = arrayListOf(1,2,3,4);						ArrayList

		var set = setOf()
			* Collections.singleton()							java.util.Collections$SingletonSet

		var set = hashSetOf(1,2,3,4);							HashSet
	
		<A,B> mapOf(pairs:Pair<A,B>)							LinkedHashMap
			

		var map = hashMapOf(1 to "1", 2 to "2", 3 to "3")		HashMap
	
	# list/set/map 支持的一些操作(扩展函数)
		last();
		min();
		max();
	
		withIndex(): Iterable<IndexedValue<T>>
			* list 数据结构的一个api
			* 返回一个迭代器, 迭代对象包含了下标和值
				public data class IndexedValue<out T>(public val index: Int, public val value: T)
		
		filter()
			* 过滤, 返回 true 的将会被留下
				arrayListOf(1,2,3,4,5).filter {it % 2 == 0} // [2, 4]
			* 如果执行对象是 map, 那么参数就是一个: entry
				mapOf("1" to "2").filter { it -> it.key == it.value }
				mapOf("1" to "2").filter { (k,v) -> k == v}
		
		filterValues()
		filterKeys()
			* map的key 和 value 过滤器
		
		mapValues()
		mapKeys()
			* map 结构的消费函数
				mapOf("1" to "2").mapKeys { println("${it.key},${it.value}") }
				mapOf("1" to "2").mapValues { println("${it.key},${it.value}") }
		
		map()
			* 一个消费函数 ,py/java都有
				arrayListOf(1,2,3,4,5).map {it * 2} // [2, 4, 6, 8, 10]
			
		
		all()
		anly()
			* 判断函数, 返回 boolean
			* 如果所有都符合条件/或者是任何一个符合条件, 返沪 true
			

		find()
			* 返回匹配成功的第一个元素
				listOf(1,4,3,4).find {it % 2 ==0} // 4
		
		count()
			* 统计 ,它也支持过滤, 返回符合条件的数量
				listOf(1,2,3,4).count {it % 2 ==0} // 2
			
		groupBy()
			* 聚合,返回的结果是一个 map<?,List<?>>, 跟 java 的stream一样
			* 把处理结果一样的数据放到一个集合,处理的结果作为key
				var result = listOf("a","bb","ccc","d","ee","fff").groupBy { it.length }
				println(result) // {1=[a, d], 2=[bb, ee], 3=[ccc, fff]}
		
		flatMap()
			* 把结果合并为一个流
			* 先把每个元素做变换, 然后再合并为一个流
				arrayOf(
				Book("Java编程思想", arrayListOf("Kevin","Litch")),
				Book("Python编程思想", arrayListOf("Ruby","xjp")),
				Book("Javascript编程思想", arrayListOf("Zy","Litch")),
				Book("C编程思想", arrayListOf("Kevin","Rocco")))

				.flatMap { it.authors }.forEach {println(it)} // 把所有的作者信息, 都组合成了一个流
		

		

		* 这些函数之间可以链式调用, 跟 Java8的 Stream 一样
	
	# 更为高效的 asSequence 
		* 一个有问题的代码
			arrayOf("1","2").map { it.length }.filter { it >= 1 }

			* 执行到 map 的时候, 会创建一个数组
			* 执行到 filer 的时候, 会创建一个数组

			* 问题在于, 每一次执行都会创建新的数组,如果操作过多, 那么严重影响性能
		
		* 可以先把需要操作的元素序列化, 使用方法: asSequence() ,在最后收集结果
			arrayOf("1","2").asSequence().map { it.length }.filter { it >= 1 }.toList()

			* 在中间不会创建任何的集合, 只有在最后收集的时候才会创建
			* 这个就跟Java的Stream一摸一样的, 如果最后没有执行收集操作, 那么中间的流处理也不会执行
			
		
		* asSequence 就是 Java 的 .stream()
		* 这是一个集合类的扩展函数, 这种东西也被成为惰性求值
	
	# 创建 sequence
		* 之前的 sequence 都是通过集合的 asSequence 来获取,
		* 也可以自己去创建, 类似于py的生成器
			var sequence = generateSequence(0) {it + 1}
			var result = sequence.takeWhile { it <= 100 }.sum()
			println(result) // 5050
			
			* generateSequence() 给定一个开始元素, 以及对元素的操作lamdba
		


	
	# 其他的一些相关函数
		var pari = Pair(v1,v2)
			* 返回一个 pair 对象,一般用于构造 Map 的一个映射
			* 这个对象就俩属性(都是泛型)
				public val first: A
				public val second: B
			
			* 该对象可以被解构赋值
				var (key, value) = Pair("name", "KevinBlandy")
				println("key=$key, value=$value")