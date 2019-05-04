--------------------------------
文档							|
--------------------------------
	# 文档的key都是字符串

	# 文档中不能重复名称的 key

	# 文档必须要有一个 _id 属性
		* 该属性值可以是任何数据类型,如果没有设置,那么系统会自动插入,默认值是 ObjectId 对象
		* 每个集合中,文档的	_id 不能重复


--------------------------------
基本命令						|
--------------------------------
	db.[colelction].insert([document])
		* 往指定的colelction插入一条或者多条记录
		* 如果是多条记录,那么参数应该是一个包含了多个document的数组
		* 如果该集合不在该数据库中, MongoDB 会自动创建该集合并插入文档
	
	db.[colelction].insertOne([document]) 
		* 插入一条记录,参数是一个document
	
	db.[colelction].insertMany([document...]) 
		* 插入多条记录,参数是一个document数组
			db.collection.insertMany([{"b": 3}, {'c': 4}])

	db.[colelction].save([document]) 
		* 如果不指定 _id 字段 save() 方法类似于 insert() 方法
		* 如果指定 _id 字段,则会更新该 _id 的数据
	

	db.[colelction].find()
		* 查看指定collection中已插入文档
	
	db.[colelction].findOne()
		* 查看指定collection中已插入文档
		* 仅仅检索一个结果
	
	db.[colelction].remove([document]);
		* 删除记录,使用一个文档参数作为条件,仅仅删除符合条件的记录
		* 如果给一个空对象({}),则删除所有的记录
		* 返回删除的数量
			{ "nRemoved" : 2 }

--------------------------------
更新							|
--------------------------------
	
	# 更新的函数,需要两个必须的参数,以及一个非必须的 config 配置项
		* 一个是负责过滤的条件
		* 一个是要更新的操作

		db.[colelction].update([query], [update],   {
			upsert: true,
			multi: true,
			writeConcern: <document>
		})
	
		query
			* update的查询条件,类似sql update查询内where后面的
		
		update
			* update的对象和一些更新的操作符(如$,$inc...)等,也可以理解为sql update查询内后面的SET
		
		* config的配置选项
			upsert
				* 可选,这个参数的意思是,如果不存在update的记录,是否插入objNew,true 为插入
				* 默认是 false 不插入
			
			multi
				* 仅仅更新符合条件的第一个,还是更新所有
			
			writeConcern
				* 抛出异常的级别
	


