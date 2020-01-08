--------------------
基本的检索			|
--------------------
	# 根据单个条件检索
		User user = queryFactory.selectFrom(QUser.user).where(QUser.user.id.eq(1)).fetchFirst();
		json(user);
	
	# 根据多个条件检索
		BooleanExpression expression1 = QUser.user.name.eq("KevinBlandy");
		BooleanExpression expression2 = QUser.user.version.lt(1);

		BooleanExpression expression3 = QUser.user.id.eq(1);
		
		queryFactory.selectFrom(QUser.user).where(expression1.or(expression2), expression3).fetchFirst();

		// 生成的条件SQL
		where (user0_.name=? or user0_.version<? ) and user0_.id=?

		* 默认 where 中的多个参数关系都为 and
	
	# 检索部分字段
		// 单个字段
		List<String> names = queryFactory.select(QUser.user.name)
				.from(QUser.user)
				.fetch();
	
		* 字段类型是什么, 结果集就是什么

		
		// 多个字段
		List<Tuple> tuples = queryFactory.select(QUser.user.name, QUser.user.id)
				.from(QUser.user)
				.fetch();
		

		* 结果集封装为 Tulple
		* 结果集还可以封装为自定义的数据类型, 通过: Projections


--------------------
分页
--------------------
	# 分页检索
		QueryResults<User> results = queryFactory.selectFrom(QUser.user)
						.offset(1)
						.limit(2)
						.fetchResults();
		
		offset(1)	设置页码, 从0开始
		limit(10)	设置每页显示记录数

		* 只要是使用了 fetchResults(), 就会进行总数量的统计查询
	
	# 结果集 QueryResults, 包含了属性
		private final long limit, offset, total;
		private final List<T> results;
	
	
	# 检索count统计
		Long count = queryFactory.selectFrom(QUser.user)
				.offset(2)
				.limit(1)
				.fetchCount();
	
	# 仅仅分页, 不统计检索
		List<User> results = queryFactory.selectFrom(QUser.user)
				.offset(2)
				.limit(1)
				.fetch();

--------------------
排序
--------------------
	# 通过 OrderSpecifier 排序
		List<User> results = queryFactory.selectFrom(QUser.user)
					.orderBy(new OrderSpecifier(Order.DESC, QUser.user.name),
							new OrderSpecifier(Order.DESC, QUser.user.id, OrderSpecifier.NullHandling.NullsFirst))
					.fetch();
	
	# OrderSpecifier 构造函数
		OrderSpecifier(Order order, Expression<T> target, NullHandling nullhandling)
		OrderSpecifier(Order order, Expression<T> target)
			order 
				* 排序方式, 枚举
					ASC
					DESC
			
			target
				* 排序字段, 对象的属性
			
			nullhandling
				* null值的处理, 枚举
					Default			默认
					NullsFirst		null记录排在前面
					NullsLast		null记录排在后面
		
	# 通过对象属性排序
		List<User> results = queryFactory.selectFrom(QUser.user)
			.orderBy(QUser.user.name.asc(),
					QUser.user.id.desc())
			.fetch();
		
		* 不能对 null 值进行处理
	

--------------------
加锁
--------------------
	# 加锁
		queryFactory.selectFrom(QUser.user).where()
			.setLockMode(LockModeType.PESSIMISTIC_WRITE)
			.fetchResults();
		

		LockModeType 枚举
			READ
			WRITE
			OPTIMISTIC
			OPTIMISTIC_FORCE_INCREMENT
			PESSIMISTIC_READ
			PESSIMISTIC_WRITE
			PESSIMISTIC_FORCE_INCREMENT
			NONE

--------------------
本地JPA查询
--------------------
	Query query = queryFactory.select(QUser.user).createQuery();

--------------------
常量
--------------------
	//TODO

	SELECT 'CONST MNAME' AS `name` ...

