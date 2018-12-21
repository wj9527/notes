---------------------
内置函数			 |
---------------------
	# 内置函数的调用都是通过 ?func 来调用的
	

---------------------
字符串相关			 |
---------------------
	boolean
		* 转换字符串为 boolean 值
		* 字符串必须是 true 或 false (大小写敏感)
		* 也可以通过 boolean_format 设置的特定格式
	
	html
		*  把html字符转义输出
	
	cap_first
		* 首字母设置大写
	
	uncap_first
		* 设置字符串中所有单词的首字母小写
	
	capitalize
		* 字符串中所有单词的首字母都大写
	
	chop_linebreak
		* 如果字符串末尾没换行,则添加换行符
	
	contains
		* 判读字符串中是否包含指定的子串,返回 boolean 

	date, time, datetime
		* 对Date类型数据的格式化
		* 参数可以是格式化的格式
		* 也可以设置默认的格式化方法
	
	ends_with
		* 判断字符串是否以指定的子串儿结尾
	
	ensure_ends_with
		* 如果字符串没有以指定的字符串结尾
		* 则会把指定的字符串添加到结尾

	ensure_starts_with
		* 同上,字符串是否以指定的字符串开头

		* 如果它指定了两个参数,那么第一个就会被解析为'正则表达式'
		* 如果它不匹配字符串的开头,那么第二个参数指定的字符串就会添加到字符串开头
		* 该方法也接受第三个标志位参数,(也就是正则表达式模式)

	
	index_of
		* 返回字字符串第一次出现的位置,没找到返回-1
	
	left_pad
		* 接收一个参数n,如果字符串不足n长度的话,会自动在前面添加空格
			${" a"?left_pad(5)}		//"    a"
		
		* 如果存在第二参数,也是一个字符, 那么这个字符串可以替换默认的空格填充策略
			${" a"?left_pad(5,"-")}		//"--- a"

		* 第二个参数也可以是个长度比1大的字符串,那么这个字符串会周期性的插入
			${" a"?left_pad(5,"abcd")}		//"abc a"	
	
	right_pad
		* 同上,不过是在后面填充

	length
		* 字符串的数量
	
	lower_case
		* 转换为小写模式
	
	upper_case
		* 转换为大写模式
	
	url
		* 对数据进行url编码
		* 它会转义所有保留的URL字符 (/,=, &,等...)
			<a href="foo.cgi?x=${x?url}&y=${y?url}">Click here...</a>
		
		* 设置URI编码的字符集
			<#setting url_escaping_charset="UTF-8">

		* 也可以在参数中指定
			${x?url('UTF-8')}
	
	url_path
		* 同上,但是它不转义斜杠(/)字符
		* 也可以指定字符集
			url_path('utf-8')
	
	number
		* 转换为数字
	
	replace
		* 使用参数2,替换参数1的子串儿
	
	remove_beginning
		*  移除指定开头的字符串,如果开头的字符串不是指定的,则返回原串儿

	remove_ending
		* 同上,移除尾部分
	
	split
		* 把字符串按照指定的子串儿分割为序列
	
	starts_with
		* 如果以指定的字符串开始,返回 true
	
	trim
		* 去掉首尾空格
	




---------------------
数值相关			 |
---------------------



---------------------
数据类型判断		 |
---------------------

	is_string		字符串
	is_sequence		序列
	is_enumerable	序列或集合
	is_collection	集合 (包含扩展的集合)
	is_collection_ex	扩展的集合 (支持 ?size)
	is_number		数字
	is_boolean		布尔值
	is_hash			哈希表 (包含扩展的哈希表)
	is_hash_ex		扩展的哈希表 (支持 ?keys 和 ?values)
	is_indexable	序列

	is_date			不要使用它！使用 is_date_like 来代替, 它们是相同的。往后也许会修改它的含义为 date_only。
	is_date_like	日期,也就似乎日期,时间或者日期-时间, 亦或者是未知精确类型的日期(从 FreeMarker 2.3.21 版本开始)
	is_date_only	日期 (没有时间部分) (从 FreeMarker 2.3.21 版本开始)
	is_time			时间 (没有年-月-日部分) (从 FreeMarker 2.3.21 版本开始)
	is_datetime		日期-时间 (包含年-月-日和时间两者)
	is_unknown_date_like	不清楚是日期或时间或日期-时间的日期
	is_method		方法
	is_transform	变换
	is_macro		宏或函数(当然,由于历史问题,也对函数有效)
	is_directive	指令类型 (例如宏 或 TemplateDirectiveModel, TemplateTransformModel, 等...), 或者函数 (由于历史问题)
	is_node			节点