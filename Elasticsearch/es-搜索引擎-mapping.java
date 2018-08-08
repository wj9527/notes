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

		