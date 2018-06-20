------------------------
基本操作				|
------------------------

------------------------
索引的操作				|
------------------------
	# 新建
		PUT		/{index_name}	
		{
			"acknowledged": true,
			"shards_acknowledged": true,
			"index": "test_index1"
		}
	
	# 删除
		DELETE	/{index_name}
		{
			"acknowledged": true
		}

------------------------
Document操作			|
------------------------
	# 新增
		PUT /{index_name}/{type_name}/{id}
	
	# 更新
		POST /{index_name}/{type_name}/{id}
	
	# 删除
		DELETE /{index_name}/{type_name}/{id}
	
	