--------------------------------
Set 和 Map 数据结构				|
--------------------------------
	1,Set
	2,WeakSet
	3,Map
	4,WeakMap

--------------------------------
Set								|
--------------------------------
	# ES6 提供了新的数据结构 Set
		* 它类似于数组,但是成员的值都是唯一的,没有重复的值
		* 跟java的set差不多是一个德行
	
	# Set 本身是一个构造函数,用来生成 Set 数据结构
		const s = new Set();
		[2, 3, 5, 4, 5, 2, 2].forEach(x => s.add(x));
		for (let i of s) {
			console.log(i);
		}
		// 2 3 5 4		没有重复的
	
	# Set 函数可以接受一个数组(或者具有 iterable 接口的其他数据结构)作为参数,用来初始化
		// 例一
		const set = new Set([1, 2, 3, 4, 4]);
		[...set]			//还可以去除数组的重复成员
		// [1, 2, 3, 4]
		
		// 例二
		const items = new Set([1, 2, 3, 4, 5, 5, 5, 5]);
		items.size // 5
		
		// 例三
		const set = new Set(document.querySelectorAll('div'));
		set.size // 56
		
		// 类似于
		const set = new Set();
		document.querySelectorAll('div').forEach(div => set.add(div));
		
		set.size // 56
			
		* 例一和例二都是Set函数接受数组作为参数,例三是接受类似数组的对象作为参数
	
	# 向 Set 加入值的时候,不会发生类型转换,所以5和"5"是两个不同的值
		* Set 内部判断两个值是否不同,使用的算法叫做"Same-value-zero equality"
		* 它类似于精确相等运算符(===),主要的区别是NaN等于自身,而精确相等运算符认为NaN不等于自身
	
		let set = new Set();
		let a = NaN;
		let b = NaN;
		set.add(a);
		set.add(b);
		console.log(set); // Set(1) {NaN}
	
		* Set 实例添加了两个NaN,但是只能加入一个,这表明,在 Set 内部,两个NaN是相等

--------------------------------
Set 实例的属性和方法			|
--------------------------------
	# 操作数据系列的方法

		Set.prototype.constructor
			* 构造函数,默认就是Set函数
		Set.prototype.size
			* 返回Set实例的成员总数
		
		
		add(value)
			* 添加某个值,返回 Set 结构本身
		delete(value)
			* 删除某个值,返回一个布尔值,表示删除是否成功
		has(value)
			* 返回一个布尔值,表示该值是否为Set的成员
		clear()
			* 清除所有成员,没有返回值

	# 遍历数据的方法
		keys()
			* 返回键名的遍历器
		values()
			* 返回键值的遍历器
		entries()
			* 返回键值对的遍历器
		forEach()
			* 使用回调函数遍历每个成员
		
		* 以上前仨方法都是返回迭代器
		* 由于 Set 结构没有键名,只有键值(或者说键名和键值是同一个值),所以keys方法和values方法的行为完全一致
		* entries方法返回的遍历器,同时包括键名和键值,所以每次输出一个数组,它的两个成员完全相等

	# Set的遍历顺序就是插入顺序,这个特性有时非常有用,比如使用 Set 保存一个回调函数列表,调用时就能保证按照添加顺序调用
	# Set 结构的实例默认可遍历,它的默认遍历器生成函数就是它的values方法
		Set.prototype[Symbol.iterator] === Set.prototype.values
		// true
		* 这意味着,可以省略values方法,直接用for...of循环遍历 Set

	# Set 结构的实例与数组一样,也拥有forEach方法,用于对每个成员执行某种操作,没有返回值
		set = new Set([1, 4, 9]);
		set.forEach((value, key) => console.log(key + ' : ' + value))
		// 1 : 1
		// 4 : 4
		// 9 : 9
	
	# 遍历应用
		* 扩展运算符(...)内部使用for...of循环,所以也可以用于 Set 结构
			let set = new Set(['red', 'green', 'blue']);
			let arr = [...set];
			// ['red', 'green', 'blue']
		
		* 扩展运算符和 Set 结构相结合,就可以去除数组的重复成员
			let arr = [3, 5, 2, 2, 5, 5];
			let unique = [...new Set(arr)];
			// [3, 5, 2]
		
		* 数组的map和filter方法也可以间接用于 Set 了
			let set = new Set([1, 2, 3]);
			set = new Set([...set].map(x => x * 2));
			// 返回Set结构：{2, 4, 6}

			let set = new Set([1, 2, 3, 4, 5]);
			set = new Set([...set].filter(x => (x % 2) == 0));
			// 返回Set结构：{2, 4}
		
		* 用 Set 可以很容易地实现并集(Union),交集(Intersect)和差集(Difference)
			let a = new Set([1, 2, 3]);
			let b = new Set([4, 3, 2]);
		
			// 并集
			let union = new Set([...a, ...b]);
			// Set {1, 2, 3, 4}
		
			// 交集
			let intersect = new Set([...a].filter(x => b.has(x)));
			// set {2, 3}
		
			// 差集
			let difference = new Set([...a].filter(x => !b.has(x)));
			// Set {1}
		
		* 如果想在遍历操作中,同步改变原来的 Set 结构,目前没有直接的方法,但是有两种变通方法
			* 一种是利用原 Set 结构映射出一个新的结构,然后赋值给原来的 Set 结构
			* 另一种是利用Array.from方法
			// 方法一
			let set = new Set([1, 2, 3]);
			set = new Set([...set].map(val => val * 2));
			// set的值是2, 4, 6

			// 方法二
			let set = new Set([1, 2, 3]);
			set = new Set(Array.from(set, val => val * 2));
			// set的值是2, 4, 6
		

--------------------------------
WeakSet							|
--------------------------------
	# WeakSet 结构与 Set 类似,也是不重复的值的集合,但是,它与 Set 有两个区别
		1,WeakSet 的成员只能是对象,而不能是其他类型的值
			const ws = new WeakSet();
			ws.add(1)
			// TypeError: Invalid value used in weak set
			ws.add(Symbol())
			// TypeError: invalid value used in weak set
		
		2,WeakSet 中的对象都是弱引用
			* 即垃圾回收机制不考虑 WeakSet 对该对象的引用
			* 也就是说,如果其他对象都不再引用该对象,那么垃圾回收机制会自动回收该对象所占用的内存,不考虑该对象还存在于 WeakSet 之中
	
	# WeakSet 的成员是不适合引用的
		* 因为它会随时消失,另外,由于 WeakSet 内部有多少个成员,取决于垃圾回收机制有没有运行,运行前后很可能成员个数是不一样的
		* 而垃圾回收机制何时运行是不可预测的,因此 ES6 规定 WeakSet 不可遍历
	
	# 语法
		//TODO
	

	
		


		
		
			

			

