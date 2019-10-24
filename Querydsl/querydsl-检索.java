--------------------
基本的条件			|
--------------------
	# 基本的根据字段检索
		UserEntity userEntity = jpaQueryFactory
			.from(qUserEntity)
			.where(qUserEntity.name.eq("Hello"))
			.fetchOne();


	# and条件
		UserEntity userEntity = (UserEntity) jpaQueryFactory
			.from(qUserEntity)
			.where(qUserEntity.name.eq("Hello").and(qUserEntity.id.eq(1)))
			.fetchOne();
		
		* where 条件参数可以有多个, 表示 and 关系
			.where(qUserEntity.name.eq("Hello"), qUserEntity.id.eq(1)) // 同上

	# or条件
		UserEntity userEntity = (UserEntity) jpaQueryFactory
			.from(qUserEntity)
			.where(qUserEntity.name.eq("Hello").or(qUserEntity.id.eq(1)))
			.fetchOne();


--------------------
连接查询			|
--------------------
	
	