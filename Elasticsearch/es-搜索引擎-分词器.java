-----------------------------
分词器						 |
-----------------------------
	# 分词器包含了几个部分
		* 在文本进行分词之前,先进行预处理
			- 过滤html标签
			- & 转换为单词 and

		* 分词,把语句拆分为单词

		* 对分词进行一些同义词,复数,时态,大小写...之类的转换
		* 这个过程叫做normalization(提升能够搜索到的结果数量)
	

	# 内置的几种分词器
		1,Standard analyzer(默认)
		2,Simple analyzer
		3,Whitespace analyzer
		4,Language analyzer

	
	