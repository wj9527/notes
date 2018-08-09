--------------------
mapping				|
--------------------
	# 自动或者手动为index中的type建立的一种数据结构和相关配置,简称为mapping
	# 查看es自动建立的mapping
		GET /index/_mapping/type
		{
		  "user": {
			"mappings": {
			  "coder": {
				"properties": {
				  "name": {
					"type": "text",
					"fields": {
					  "keyword": {
						"type": "keyword",
						"ignore_above": 256
					  }
					}
				  },
				  "age": {
					"type": "long"
				  },
				  "foo": {
					"type": "long"
				  },
		...
	
	# 搜索结果不一致的问题
		* ES自动建立mapping的试试,设置了不同的field不同的data type
		* 不同的data type的数据类型,分词,搜索行为是不一样的,所以出现了 _all,field的搜索表现不一样

	
	# 透彻理解mapping
		1,往es里面直接插入数据,es会自动建立索引,同时建立type以及对应的mapping
		2,mapping中就自动定义了每个field的数据类型
		3,不同的数据类型(text,data),可能有的是exact value,有的是full text
		4,exact value 在建立倒排索引,分词的时候,是将整个值一起作为一个关键字建立到倒排索引中的
		5,full text 就会进过各种处理(分词,normalizationm....),建立在倒排索引中
		6,exact value 和 full text 的field在被搜索的时候,query string 也会经过相同的行为处理
			* exact value 全值匹配
			* full text 先对query string进行分词,normalizationm,再进行匹配
		7,可以用es的dynamic mapping,让其自动建立mapping(自动设置数据类型),也可以手动的提前为index创建mapping,手动对每个field设置数据类型,索引行为,分词器...
		8,mapping,就是index的type的元数据,每个type都有一个自己的mapping,决定了数据类型,建立倒排索引的行为,还有进行搜索的行为
