-------------------------
document - 查询相关
-------------------------
	# 基本的查询
		db.[collection].find([condition])
			* 根据条件检索出结果
			* 如果没有条件, 则检索出所有的数据
			
			* 该方法返回的对象是一个 '迭代器', 可以使用 while 来进行迭代
				const it = db.foo.find();
				while (it.hasNext()) {
					printjson(it.next());
				}
			
			* 可以使用 pretty() 函数, 格式化json结果
				db.foo.find().pretty;
			

	
	# 分页
		db.[collection].find([condition]).skip([rows])
			* 用skip()方法来跳过指定数量记录条数的数据

		db.[collection].find([condition]).limit([rows])
			* 使用 limit 方法, 来限制结果集数量
	
	
	# 统计数量
		db.[collection].count([condition]);
			* 根据条件统计文档的数量
			* 如果没有条件, 则统计所有的文档

	# 排序
		db.[collection].find().sort([row])
			* 通过 sort 指定排序的字段以及策略
				db.users.find().sort({name: -1}) // 根据name字段，逆序排序
			
			* 排序策略。-1: 逆序，1:升序

-------------------------
document - 聚合检索
-------------------------

-------------------------
document - 条件语句
-------------------------
	# 基本查询条件
		等于				{<key>:<value>}			db.col.find({"by": "菜鸟教程"}).pretty()		where by = '菜鸟教程'
		小于				{<key>:{$lt:<value>}}	db.col.find({"likes":{$lt: 50}}).pretty()		where likes < 50
		小于或等于			{<key>:{$lte:<value>}}	db.col.find({"likes":{$lte: 50}}).pretty()		where likes <= 50
		大于				{<key>:{$gt:<value>}}	db.col.find({"likes":{$gt: 50}}).pretty()		where likes > 50
		大于或等于			{<key>:{$gte:<value>}}	db.col.find({"likes":{$gte: 50}}).pretty()		where likes >= 50
		不等于				{<key>:{$ne:<value>}}	db.col.find({"likes":{$ne: 50}}).pretty()		where likes != 50
	
	# $type 操作符
		* $type操作符是基于BSON类型来检索集合中匹配的数据类型，并返回结果。
		
			类型					数字	备注
			Double					1	 
			String					2	 
			Object					3	 
			Array					4	 
			Binary data				5	 
			Undefined				6		已废弃。
			Object id				7	 
			Boolean					8	 
			Date					9	 
			Null					10	 
			Regular	Expression		11	 
			JavaScript				13	 
			Symbol					14	 
			JavaScript	(with scope)15	 
			32-bit integer			16	 
			Timestamp				17	 
			64-bit integer			18	 
			Min key					255		Query with -1.
			Max key					127	 
		
		* 根据指定key的类型去匹配数据
			db.users.find({name: {$type: 'string'}});  // 仅仅匹配name属性是字符串的记录
			db.users.find({name: {$type: 2}});		// 同上

	
	# 条件关系
		* AND 关系, 默认对象中的属性都是 AND 条件
			db.col.find({name: "1", age: 23}); // WHERE name = '1' AND age = 23;
		
		* OR 关系
			db.col.find({$or: [{name: "v"}, {name: "z"}]}); // WHERE name = 'v' OR `name` = `z`
		
		* 组合关系
			db.users.find({
				name: "KevinBlandy",
				$or: [{
					age: {
						$gt: 18
					}
				}, {
					age: {
						$lt: 50
					}
				}]
			});  // WHERE name = 'KevinBlandy' AND (age > 18 OR age < 50)
			