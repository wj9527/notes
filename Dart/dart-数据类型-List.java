-----------------------------
List
-----------------------------
	# 集合的初始化
		var arr = [];				
		List arr = [1, 2, 3, '4'];	
		List arr = new List(2);  
			* 通过参数指定List的长度, 默认使用 null 填充
			* 当使用构造方法初始化的
	
		* 在不使用泛型的情况下, 一个list中可以存储不同类型的元素
	
	# List 类的构造方法
		// TODO

	# 数组的长度
		* 通过 length 属性获取到
		* 使用字面量的形式初始化, 数组长度会随着元素的新增而自增
			var arr = [];
			arr.add(1);
			arr.add(2);
			arr.add(3);
			print(arr);		// [1, 2, 3]
		
		* 固定了数组的length (通过构造方法), 不允许使用 add 方法, 只能通过下标去操作元素
			var arr = new List(1);
			arr.add(1);// Unsupported operation: Cannot add to a fixed-length list
			print(arr);
		
		* 数组操作到了下标以外的元素, 会发生越界异常
			var arr = [];
			arr[0] = 1;  // RangeError (index): Invalid value: Valid value range is empty: 0
			print(arr);
			
	
	# 泛型的 List
		* 泛型 list 的定义必须要通过声明 List 属性来定义变量, 不能使用 var 或者 dynamic
			List<String> arr = ['123', '123']; // ok

			List<String> arr = new List(2);
			arr[0] = 1; // Error: A value of type 'int' can't be assigned to a variable of type 'String'.

		* 存储泛型以外的数据, 会抛出异常
		

-----------------------------
List - 实例方法
-----------------------------

-----------------------------
List - 静态方法
-----------------------------
