-------------------------
jpql
-------------------------
	# JPQL
		* 支持使用 AS 关键字起别名
		* 不支持使用;结束, 不支持使用``
	
	# JPQL参数占位
		* 使用位置占位
			1? 2? 3? 
		
		* 使用别名占位
			:name, :age

	# 检索
		FROM io.app.domain.User 
			* 不能写SELECT, 直接 FROM 对象
			* 如果对象被EntityManager管理, 可以用简写, 否则用全名
	
	# 模糊查询
		FROM io.app.domain.User  WHERE name LIKE ?1
			* 占位参数, 需要自己添加 % 符号
	
	# 排序
		FROM User ORDER BY name DESC, id ASC
	
	
	# 统计
		SELECT COUNT(1) FROM User
	
	# 更新
		UPDATE User SET name = ?1 WHERE id = ?2

	# 删除
		DELETE User WHERE id = ?1
	

	

	
