-------------------------
document 
-------------------------

-------------------------
document - id
-------------------------
	# 在集合中插入文档时，如果没有在字段名称中添加带有_id的字段名称，则MongoDB将自动添加一个Object id字段
		{"_id": ObjectId("xxxxxxxxx")}
	



-------------------------
document - 基本命令
-------------------------
	db.[collection].insert([document]);
		* 往指定的collection插入一个或者多个(参数用数组)document
		* 如果 collection 不存在, 会创建

		* 执行成功后返回插入成功的文档数量
			WriteResult({"nInserted": 1});
			
	db.[collection].remove([condition]);
		* 根据条件移除数据
		* 如果没有条件, 则移除所有数据


-------------------------
document - 更新
-------------------------
	db.[collection].update([condition], [update], [config]);
		* 根据 condition 执行 update 修改一条文档
			db.users.update({name: "KevinBlandy"}, {$set: {name: "new Name"}}); // UPDATE `users` SET `name` = 'new Name' WHERE `name` = 'KevinBlandy';
		
		* config 选项
			{
				upsert: true,
					*  可选，如果不存在update的记录，是否插入objNew ,true为插入，默认是false，不插入。

				multi: true,
					* 可选，默认是false,只更新找到的第一条记录，如果这个参数为true,就把按条件查出来多条记录全部更新。

				writeConcern: <document>
					* 可选，抛出异常的级别。
			}
	
	
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
		
	
	# 分页
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
		
	