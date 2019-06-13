------------------------
mapping					|
------------------------
	# 跟关系型数据库的DDL一样, 定义存储的数据类型, 索引啥的


	# 查看指定索引的mapping
		GET /<index>/_mapping

	# mapping的结构
		{
			"<index>":{
				"mappings":{
					"properties":{
						"<field>":{

						}
					}
				}
			}
		}

		* properties 下就是每个字段对应的'配置'
			- 数据类型
			- 如何分词
			- ... ...
		* properties 属性下可以嵌套 properties
	

	
	# mapping 可以在创建 index 之前创建

	# mapping 也可以在创建 index 之后修改

	
------------------------
mapping	字段属性		|
------------------------
	# type
		* 字段的数据类型, 如果不手动设置, 则自动捕获

		text
		date
		long

------------------------
mapping	字段属性		|
------------------------
	{
	  "<index>" : {
		"mappings" : {
		  "properties" : {
			"<field>" : {
			  "properties" : {
				"<field>" : {
				  "type" : "text",
				  "fields" : {
					"keyword" : {
					  "type" : "keyword",
					  "ignore_above" : 256
					}
				  }
				}
			  }
			},
			"<field>" : {
			  "type" : "text",
			  "fields" : {
				"keyword" : {
				  "type" : "keyword",
				  "ignore_above" : 256
				}
			  }
			},
			"<field>" : {
			  "type" : "date"
			},
			"<field>" : {
			  "type" : "long"
			}
		  }
		}
	  }
	}
