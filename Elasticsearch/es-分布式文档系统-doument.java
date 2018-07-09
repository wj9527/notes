--------------------
document的更新		|
--------------------
	# 更新(1)
		POST /{index_name}/{type_name}/{id}	
		{
		  "_index": "test_index",
		  "_type": "product",
		  "_id": "1",
		  "_version": 2,			
		  "result": "updated",
		  "_shards": {
			"total": 2,
			"successful": 1,
			"failed": 0
		  },
		  "_seq_no": 1,
		  "_primary_term": 1
		}

		* 请求体直接提交需要修改的字段即可
		* 不管本次提交,是否有成功修改字段,result值永远为:'updated'
		* _version字段必会加1
		* 可以理解为强制更新
	
	# 更新(2)
		POST /{index_name}/{type_name}/{id}/_update
		* 该种方式,提交的JSON体有所变化
			{
				"doc":{
					//需要修改的字段
				}
			}
		* 如果本次提交未修改数据的话,那么result字段值为:'noop',并且没有:'_seq_no'和'_primary_term'字段,version也不会+1
		* 可以理解为非强制更新,仅仅更新需要更新的字段


	# 全量替换
		PUT  /{index_name}/{type_name}/{id}	

		* 如果id已经存在,那么原来的document不会被立即删除,而是会被标记为: delete
		* 当es中数据越来越多的时候,es会在后台自己动的把标记为:delete 的document物理删除掉
	
	# 对比
		* 全量替换的话,version会被重置为1,更新的话,version会加1
		* 更新情况下,result字段值为"updated"
		* 全量替换不好,替换的情况下,必须带上所有的数据
		* 更新则,仅仅需要提供更新的字段即可

--------------------
document的强制创建	|
--------------------
	# 创建文档域全量替换的语法是一样的
	# 如果只想新建文档,不想替换文档,那么就需要强制创建(两种方式)
		PUT /index/type/id?op_type=create
		PUT /index/type/id/_create
			* 这种方式比较常见
	
		* 如果该id的document已经存在,那么不会PUT成功,会抛出异常

--------------------
document的删除		|
--------------------
	# document不会被立即的物理删除,只会被标记为delete,当数据越来越多的试试,在后台自动的删除

		DELETE /index/type/id
