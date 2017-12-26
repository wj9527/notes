-----------------------
子节点					|
-----------------------
	* 操作文档树最简单的方法就是告诉它你想获取的tag的name
		soup.head.title
		# 获取文档中 head 标签下的 titile 标签
		
		* 通过这种方式,只能获取到指定名称的第一个标签
	
	* 获取文档中的所有标签,需要使用 find_all(),返回list
		soup.find_all('a')
	
	* 根据属性获取子节点信息
		contents 
			* 返回list,当前标签'所有直接子元素集合',包括文本节点(bs4.element.NavigableString)
			* '字符串节点没有该属性'
		
		children 
			* 返回迭代器,当前标签的'所有直接子元素'
		
		descendants
			* 返回当前标签的'所有子元素'
			* 递归
	
	* 如果tag只有一个 NavigableString 类型子节点,那么这个tag可以使用 .string 得到子节点
		soup.head.title.string

		* 如果tag只有一个子标签,这个tag也可以直接通过 .string 获取到唯一子标签的标签体
			<head><title>Title</title></head>
			soup.head.string
			# Title
	
	* 通过 strings 获取所有的子文本信息,递归获取
		soup.strings

		* 返回的是迭代器
		* 使用 stripped_strings 可以忽略掉所有的空白信息
	
	
		
		
