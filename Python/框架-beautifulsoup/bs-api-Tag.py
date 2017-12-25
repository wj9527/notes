--------------------------------
beautifulsoup-Tag			 	|
--------------------------------
	* <class 'bs4.element.Tag'>


--------------------------------
beautifulsoup-属性			 	|
--------------------------------
	name
		* 返回标签名称

	attrs
		* 返回属性dict
	
	contents 
		* 返回list,当前标签'所有直接子元素集合',包括文本节点(bs4.element.NavigableString)
		* '字符串节点没有该属性'
	
	children 
		* 返回迭代器,当前标签的'所有直接子元素'
	
	descendants
		* 返回当前标签的'所有子元素'
		* 递归
	
	string
		* 获取当前标签的文本标签
	
	strings
		* 获取当前标签下的所有文本标签,递归获取
	
	stripped_strings 
		* 同上.空白会被忽略掉

--------------------------------
beautifulsoup-方法			 	|
--------------------------------
	get(attr)
		* 获取指定名称的属性值,属性不存在返回None
	
	find_all(tag_name)
		* 获取子标签中指定名称的标签对象,返回list