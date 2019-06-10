------------------------------
DSL							  |
------------------------------
	# 文档
		https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl.html
	
------------------------------
DSL	query					  |
------------------------------
	# 匹配所有
		{"query": { "match_all": {} }}
	
	# 精准匹配指定的字段名称
		{
		  "query": { "match": { "<field>": <value> } }
		}

		* 如果允许匹配多个字段, 那么 value 可以有多个, 使用逗号分隔
			{
			  "query":{"match": {"name":"Litch Rocck"}} //name = "Litch" or name = "Rocck"
			}
		
		* 如果参数里面确实有空格,需要精准匹配那么需要使用:match_phrase
			{
			  "query":{"match_phrase": {"name":"Litch Rocck"}} //name = "Litch Rocck"
			}

------------------------------
DSL	bool					  |
------------------------------
	# 把多个条件使用布尔逻辑将较小的查询组成更大的查询
	# filter
	# and 关系用:must
		{
		  "query": {
			"bool": {
			  "must": [
				{ "match": { "name": "KevinBlandy" } },
				{ "match": { "age": "23" } }
			  ]
			}
		  }
		}
		* 该bool返回一个判断条件:name = "KevinBlandy" and age = "23"
	
	# or关系用:should
		{
		  "query": {
			"bool": {
			  "should": [
				{ "match": { "name": "Litch" } },
				{ "match": { "name": "Roccl" } }
			  ]
			}
		  }
		}
		* 该 bool 返回一个判断条件:name = "Litch" or name = "Roccl"
		
	# 结果取反用:must_not
		{
		  "query": {
			"bool": {
			  "must_not": [
				{ "match": { "name": "Litch" } },
				{ "match": { "name": "Roccl" } }
			  ]
			}
		  }
		}
		* 该 bool 返回一个判断条件:name != "Litch" and name != "Roccl"
	
	# 多个条件可以组合
		{
		  "query":{
			"bool":{
			  "should": [{
				  "match": {
				  "name": "Litch"
				}}
			  ],
			  "must_not": [{
				  "match": {
				  "age": 24
				}}
			  ]
			}
		  }
		}
		* 该 bool 返回一个判断条件:name = "Litch" and 24 != 24


------------------------------
DSL	分页					  |
------------------------------
	# 使用 from & size		
		{"from":0,	"size":10}

------------------------------
DSL	排序					  |
------------------------------
	# 使用 sort
		{"sort": { "<field>": { "order": "<desc/asc>" } }}

------------------------------
DSL	限制结果字				  |
------------------------------
	# 使用 _source
		{
			"_source": ["<field1>", "<field2>"]
		}

		* 通过 _source 指定n多个要检索的字段, 字段支持对象导航


	