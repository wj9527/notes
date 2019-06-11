-------------------
基本的根据条件检索 |
-------------------
	# 请求
		GET /<index>/_search?q=<query>
			
		
		{
		  "took" : 2,
		  "timed_out" : false,
		  "_shards" : {
			"total" : 1,
			"successful" : 1,
			"skipped" : 0,
			"failed" : 0
		  },
		  "hits" : {
			"total" : {
			  "value" : 13,
			  "relation" : "eq"
			},
			"max_score" : 1.0,
			"hits" : [
			  {
				"_index" : "customer",
				"_type" : "_doc",
				"_id" : "TsVMQGsBor31qRgUxQmS",
				"_score" : 1.0,
				"_source" : {
				  "name" : "KevinBlandy"
				}
			  }
			]
		  }
		}

		* 如果不添加检索参数q,则会匹配出所有的记录
		* 结果默认只会检索10条记录
	

-------------------
query参数		   |
-------------------
	# 过滤参数:q
		* 
			* 检索所有, q=*

		<field>:<value>
			* 精准匹配, q=name:KevinBlandy
	
	# 排序擦数:sort
		sort=<field>:<asc/desc>

		* 如果有多个, 使用逗号分隔:sort=age:asc,_id:desc
	
	# 分页参数:size & from
		* size,每页显示的记录数量
		* from,从第几条数据开始检索,0表示第一条
	
		* deep paging问题
			* deep paging,简单来说,就是搜索得特别深,比如1000000条数据,每页显示10条,此时需要检索最后一页的数据
			* 符合请求的数据,可能存在于多个primary shard,replica shard,于是就要把所有数据汇总到 coordinating node(协调节点)
			* 由协调节点进行排序,取出最符合条件的数据,按照分页返回
			* 这个过程耗费带宽,内存,网络计算,这个就是deep paging问题,我们的开发尽量要避免这种情

	
	# _source 数据过滤参数:
		_source
			* 检索数据是否要携带 _source 数据, 值可以是 true/false
			* 也可以通过该参数来指定要检索的字段
				GET /goods/_doc/1?_source=author.name,author.age
			
		_source_includes
		_source_excludes
			* 过滤/包含指定的 _source 数据
		
		* 支持有多个值, 使用否号分割
		* 支持通配符:*
				GET /goods/_doc/1?_source=*.name
	
	# stored_fields
		//TODO
	

-------------------
元数据			   |
-------------------

	took
		* 执行搜索的时间（以毫秒为单位）
	timed_out 
		* 搜索是否超时
	
		_shards.id
			* 搜索了多少个分片
		_shards.successful
			* 搜索成功的分片数量
		_shards.skipped
			* 跳过的分片数量
		_shards.failed
			* 搜索失败的分片数量

		hits.total.value
			* 在包含的搜索条件匹配的文档总数的信息的对象
			* 总命中数的值

		hits.total.relation
			* hits.total.value是否是确切的命中计数
			* 在这种情况下它等于"eq"或总命中数的下限(大于或等于), 在这种情况下它等于gte

		hits.hits[x].sort
			* 对结果进行排序键
		
		hits.hits[x]._source
			* 实际的搜索结果

	