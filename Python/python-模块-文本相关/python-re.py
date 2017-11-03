------------------------
re						|
------------------------
	* 正则表达式
	* 用于对字符串的操作
	* 在 string 中涉及到正则的操作
		replace(reg,new)	# 替换
		find(reg,start,end)	# 检索
		rfind(reg,start,end)# 检索
		split(reg,coun)		# 切割
	* 但是 string 本身对于正则操作的 api 有限,对于其他要求苛刻的匹配,就需要使用  re 模块
	* 操作体系
		一,直接使用re模块函数方法进行正则操作
			* 看模块函数

		二,创建正则对象(pattern)来进行正则操作(效率比较高)
			1,获取到正则对象(pattern)
				_sre.SRE_Pattern compile(pattern,flags)
				var pattern = compile('')

			2,进行匹配,检索操作
				_sre.SRE_Match match(str,start,end)
				_sre.SRE_Match search(str,start,end)
				callable_iterator finditer(str,start,end)
				...
			
			3,从结果 _sre.SRE_Match 中获取数据
			

			
------------------------
re-内置属性				|
------------------------

------------------------
re-模块级别函数			|
------------------------
	list findall(pattern, string, flags)
		* 使用 pattern 表达式去匹配出 string 中的所有符合规则的元素
		* flags,用于指定匹配模式,默认值为 0
		
	list split(pattern, string)
		* 分隔
	
	str sub(pattern,repl,string,count=0,flags=0)
		* 正则,目标字符串,替换值,替换多少次

	callable_iterator finditer(pattern, string, flags)
		* 使用 pattern 表达式去匹配出 string 中的所有符合规则的元素,返回迭代器
	
	_sre.SRE_Match search(pattern, string, flags)
		* 返回 string 第一个符合 pattern 的子串儿,匹配失败,返回 None

	_sre.SRE_Match match(pattern, string, flags)
		* 判断 string 是否符合 pattern,符合返回 _sre.SRE_Match ,否则返回 None
		* '仅仅匹配开头,匹配成功后,string还有剩余字符,也视为匹配成功'

	_sre.SRE_Pattern compile(pattern,flags)
		* 把一个字符串编译成 pattern 对象用于匹配或搜索

------------------------
_sre.SRE_Pattern 方法	|
------------------------
	_sre.SRE_Match match(str,start,end)
		* 匹配 str 字符串,匹配成功,返回 _sre.SRE_Match 对象,匹配失败返回 None
		* start,end 默认值为字符串的第一个最后一个
	
	_sre.SRE_Match search(str,start,end)
		* 返回 str 字符串,匹配到的第一个值,匹配失败返回 None
		* start,end 默认值为字符串的第一个最后一个
		* '仅仅匹配开头,匹配成功后,string还有剩余字符,也视为匹配成功'
	
	list findall(str,start,end)
		* 返回 str 字符串,匹配到的所有值
		* start,end 默认值为字符串的第一个最后一个
	
	callable_iterator finditer(str,start,end)
		* 返回 str 字符串,匹配到的所有值,返回的是一个迭代器
		* start,end 默认值为字符串的第一个最后一个

------------------------
_sre.SRE_Match 方法		|
------------------------

	str group(num)
		* 返回 match object 中的字符串.
		* 每一个 ( ) 都是一个分组,分组编号从1开始,从左往右,每遇到一个左括号,分组编号+1
		* 组 0 总是存在的,它就是整个表达式(原字符串)
		* 没有参数时,num默认为0这时返回整个匹配到的字符串
		* 指定一个参数(整数)时,返回该分组匹配到的字符串
		* 指定多个参数时,返回由那几个分组匹配到的字符串组成的 tuple
	
	tuple groups()
		* 获取到所有的匹配项