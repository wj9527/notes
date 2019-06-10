--------------------
document			|
--------------------


--------------------
document - 创建		|
--------------------
	# 基本的创建
		PUT /<index>/_doc/<id>?pretty
		{...}

		{
		  "_index" : "customer",
		  "_type" : "_doc",
		  "_id" : "1",
		  "_version" : 4,
		  "result" : "updated",
		  "_shards" : {
			"total" : 2,
			"successful" : 1,
			"failed" : 0
		  },
		  "_seq_no" : 3,
		  "_primary_term" : 1
		}

		* 如果Index不存在, 会自动的创建
		* PUT方式, 必须手动的指定id属性

	# 使用POST创建
		POST /<index>/_doc
		{...}

		* POST方式, 如果不指定id的话, 系统自动为doc生成一个uuid(VMVNQGsBor31qRgUZwnr)

	# 强制创建
		* 如果只想新建文档,不想替换文档,那么就需要强制创建(两种方式)

		PUT /<index>/_doc/<id>?op_type=create

		PUT /<index>/_doc/<id>/_create
			* 这种方式比较常见
	
		* 如果该id的document已经存在,那么不会PUT成功,会抛出异常


--------------------
document - 检索		|
--------------------
	# 基本的根据id检索
		GET /<index>/_doc/<id>

		{
		  "_index" : "customer",
		  "_type" : "_doc",
		  "_id" : "1",
		  "_version" : 4,
		  "_seq_no" : 3,
		  "_primary_term" : 1,
		  "found" : true,
		  "_source" : {
			...
		  }
		}

--------------------
document - 更新		|
--------------------
	# 全量替换更新(PUT)
		PUT /<index>/_doc/<id>
		{...}

		{
		  "_index" : "customer",
		  "_type" : "_doc",
		  "_id" : "1",
		  "_version" : 2,
		  "result" : "updated",
		  "_shards" : {
			"total" : 2,
			"successful" : 1,
			"failed" : 0
		  },
		  "_seq_no" : 1,
		  "_primary_term" : 1
		}
		
		* 相当于执行了一次覆盖
		* 如果id已经存在,那么原来的document不会被立即删除,而是会被标记为: delete
		* 当es中数据越来越多的时候,es会在后台自己动的把标记为:delete 的document物理删除掉
		* _version 始终会 +1
	
	# 强制更新(全部更新)
		POST /<index>/_doc/<id>
		{...}

		* 请求体需要提交所有字段,不存在的字段会被删除
		* 不管本次提交,是否有成功修改字段,result值永远为:'updated'
		* 不管是有修改,_version字段必会加1
		* 可以理解为强制更新
		* 如果指定id的数据不存在(或者未指定id), 则会创建, 则 "result" = "created"
	
	# 非强制更新(部分更新)
		POST /<index>/_update/<id>
		{"doc":{...}}

		{
		  "_index" : "customer",
		  "_type" : "_doc",
		  "_id" : "1",
		  "_version" : 5,
		  "result" : "noop",
		  "_shards" : {
			"total" : 0,
			"successful" : 0,
			"failed" : 0
		  }
		}


		* 该种方式,提交的JSON体有所变化
			{
				"doc":{
					//需要修改的字段
				}
			}

		* 可以仅仅提交更新需要更新的字段
		* 如果本次提交未修改数据的话,那么result字段值为:'noop',并且没有:'_seq_no'和'_primary_term'字段,
		* 本次提交有修改数据的是,跟强制更新的返回结果是一样的
		* 只有在数据有修改的时候,version +1
		* 可以理解为非强制更新
		* partial update(部分更新)
	
	# 也支持使用脚本语言更新
		POST /<index>/_update/<id>
		{
		  "script" : "ctx._source.age += 5"
		}

		* 具体看脚本
	
	# 更新时, Elasticsearch都会删除旧文档, 然后一次性对应用了更新的新文档编制索引

	
--------------------
document - 删除		|
--------------------
	# 基本的根据id删除
		DELETE /<index>/_update/<id>?pretty

		{
		  "_index" : "customer",
		  "_type" : "_doc",
		  "_id" : "11",
		  "_version" : 3,
		  "result" : "deleted",
		  "_shards" : {
			"total" : 2,
			"successful" : 1,
			"failed" : 0
		  },
		  "_seq_no" : 23,
		  "_primary_term" : 1
		}

		* 如果删除的数据不存在, 则"result" = "not_found"
		* 如果需要删除所有的doc, 建议直接删除整个索引,这样更高效
	
	
	# document不会被立即的物理删除,只会被标记为delete,当数据越来越多的试试,在后台自动的删除
		
