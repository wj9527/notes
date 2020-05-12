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
			
	db.[collection].remove([condition], [config]);
		* 根据条件移除数据
		* 如果没有条件, 则移除所有数据
		
		* config
			{
				justOne: false,
					* 如果设为 true 或 1，则只删除一个文档
					* 如果不设置该参数，或使用默认值 false，则删除所有匹配条件的文档。
				writeConcern: <document>
					* 可选，抛出异常的级别。
			}
		
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
	
	db.[collection].save([document], [config]);
		* 通过传入的文档来替换已有文档

		* config 选项
			{
				writeConcern: <document>
					* 可选，抛出异常的级别。 
			}
		
	
