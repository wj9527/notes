----------------------------
索引						|
----------------------------
	# 通过 explain() 来查看检索效率
		 db.users.find().explain();
			{
					"queryPlanner" : {
							"plannerVersion" : 1,
							"namespace" : "test.users",
							"indexFilterSet" : false,
							"parsedQuery" : {

							},
							"winningPlan" : {
									"stage" : "COLLSCAN",
									"direction" : "forward"
							},
							"rejectedPlans" : [ ]
					},
					"serverInfo" : {
							"host" : "KevinBlandy",
							"port" : 27017,
							"version" : "4.0.9",
							"gitVersion" : "fc525e2d9b0e4bceff5c2201457e564362909765"
					},
					"ok" : 1
			}
	
	# 创建索引
		db.users.createIndex({'name':1});
			* 在集合 users 上的 'name' 字段建立索引
				{
						"createdCollectionAutomatically" : false,
						"numIndexesBefore" : 1,
						"numIndexesAfter" : 2,
						"ok" : 1
				}
					
			* 1 表示正序排序的索引,使用 -1 表示为逆序排序的索引

		* mongobd一个集合最多允许 64 个索引
		* 索引会提高检索效率,但是在执行修改,插入的时候会去执行修改索引.都将会耗费更多的时间
		* 也可以定义多个字段,为复合索引
			db.users.createIndex({'name':1,'age': 1});
		
		* 创建索引还支持第三个 options 参数(JSON)
			background	Boolean			
				* 建索引过程会阻塞其它数据库操作,background可指定以后台方式创建索引,即增加 "background" 可选参数。
				* 默认值为false。
			unique	Boolean			
				* 建立的索引是否唯一,指定为true创建唯一索引,默认值为false
			name	string			
				* 索引的名称,如果未指定,MongoDB的通过连接索引的字段名和排序顺序生成一个索引名称
			dropDups	Boolean			
				* 3.0+版本已废弃,在建立唯一索引时是否删除重复记录,指定 true 创建唯一索引,默认值为 false

			sparse	Boolean			
				* 对文档中不存在的字段数据不启用索引
				* 这个参数需要特别注意,如果设置为true的话,在索引字段中不会查询出不包含对应字段的文档,默认值为 false
			expireAfter	Seconds	integer	
				* 指定一个以秒为单位的数值,完成 TTL设定,设定集合的生存时间
			v					
				* index version	索引的版本号
				* 默认的索引版本取决于mongod创建索引时运行的版本
			weights	document		
				* 索引权重值,数值在 1 到 99,999 之间,表示该索引相对于其他索引字段的得分权重
			default_language	string			
				* 对于文本索引,该参数决定了停用词及词干和词器的规则的列表,默认为英语
			language_override	string			
				* 对于文本索引,该参数指定了包含在文档中的字段名,语言覆盖默认的language,默认值为 language
			
	
	# 查看集合创建的索引
		db.[collection].getIndexes()
	
	# 查看集合索引的大小
		db.[collection].totalIndexSize()
	
	# 删除集合的索引
		db.[collection].dropIndex(name)
			* 删除指定名称的索引

		db.[collection].dropIndexes()
			* 删除所有的索引
	

