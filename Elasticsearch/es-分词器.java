---------------------
分词器				 |
---------------------
	# 分词器

	# 切分词语 & normalization(提升recall召回率)
		* 对数据进行分词, 并且对每个单词进行normalization
		* 召回率:搜索的时候, 增加能够搜索到的结果数量
	

	# 分词器包含了几个部分
		character filter
			* 分词之间, 进行预处理, 例如:过滤html标签

		tokenizer
			* 分词

		token filter
			* 会执行normalization的一些操作, 例如:同义词转换, 大小写转换
	

		
	
---------------------
内置的分词器		 |
---------------------
	Standard analyzer(默认)
	Simple analyzer
	Witespace analyzer
	Language analyzer

	