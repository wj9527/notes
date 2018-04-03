----------------------------
Iterator					|
----------------------------
	1,Iterator(遍历器)的概念
	2,默认 Iterator 接口
	3,调用 Iterator 接口的场合
	4,字符串的 Iterator 接口
	5,Iterator 接口与 Generator 函数
	6,遍历器对象的 return(),throw()
	7,for...of 循环

----------------------------
Iterator(遍历器)的概念		|
----------------------------
	# Iterator 的作用有三个
		* 一是为各种数据结构,提供一个统一的,简便的访问接口
		* 二是使得数据结构的成员能够按某种次序排列
		* 三是 ES6 创造了一种新的遍历命令for...of循环，Iterator 接口主要供for...of消费
	#Iterator 的遍历过程是这样的
		1,创建一个指针对象，指向当前数据结构的起始位置。也就是说，遍历器对象本质上,就是一个指针对象
		2,第一次调用指针对象的next方法，可以将指针指向数据结构的第一个成员
		3,第二次调用指针对象的next方法，指针就指向数据结构的第二个成员
		4,不断调用指针对象的next方法，直到它指向数据结构的结束位置
	# 每一次调用next方法,都会返回数据结构的当前成员的信息
	# 具体来说,就是返回一个包含value和done两个属性的对象
		* 其中,value属性是当前成员的值
		* done属性是一个布尔值,表示遍历是否结束
	
	# 模拟next方法返回值的例子
		function makeIterator(array) {
			var nextIndex = 0;
			return {
				next: function() {
					if(nextIndex < array.length){
						return {value: array[nextIndex++], done: false};
					}else{
						return {value: undefined, done: true};
					}
				}
			};
		}
		
		var it = makeIterator(['a', 'b']);
		
		console.log(it.next());	// { value: "a", done: false }
		console.log(it.next());	// { value: "b", done: false }
		console.log(it.next());	// { value: undefined, done: true }
	
		* 对于遍历器对象来说,done: false和value: undefined属性都是可以省略的
		* 因此上面的makeIterator函数可以简写成下面的形式

		function makeIterator(array) {
			var nextIndex = 0;
			return {
				next: function() {
					if(nextIndex < array.length ){
						//省略done,表示还未完成
						return {value: array[nextIndex++]}
					}else{
						//省略value,表示最后一个元素
						return {done: true};
					}
				}
			};
		}

	# 无限运行的遍历器对象的例子
		function idMaker(){
			let id = 1;
			return {
				next:function(){
					return {
						value:id++,
						done:false
					}
				}
			}
		}
		let it = idMaker();
		
		console.log(it.next());
		console.log(it.next());
		console.log(it.next());
		console.log(it.next());
		console.log(it.next());


----------------------------	
默认 Iterator 接口			|
----------------------------
	
