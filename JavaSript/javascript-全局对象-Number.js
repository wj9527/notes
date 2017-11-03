------------------------
Number					|
------------------------
	# 数字类型的包装值
		var x = 5;
		var y = new Number(5);
		x == y		//true
		x === y		//false

	# 创建
		var num = new Number(value);
			* 如果一个参数值不能转换为一个数字将返回 NaN (非数字值)。

	# 静态属性
		MAX_VALUE	
			* 可表示的最大的数。
		MIN_VALUE	
			* 可表示的最小的数。
		NEGATIVE_INFINITY	
			* 负无穷大，溢出时返回该值。
		NaN	
			* 非数字值。
		POSITIVE_INFINITY	
			* 正无穷大，溢出时返回该值。
	
	# 实例方法
		toExponential(x)	
			* 把对象的值转换为指数计数法。
			* 如果超出,4舍5入,不足,补0

		toFixed(x)
			* 把数字转换为字符串，结果的小数点后有指定位数的数字。
			* 如果超出,4舍5入,不足,补0

		toPrecision(x)
			* 把数字格式化为指定的长度。
			* 如果超出,4舍5入,不足,补0

		toString()
			* 把数字转换为字符串，使用指定的基数。

		valueOf()
			* 返回一个 Number 对象的基本数字值。
			* 返回的是10进制