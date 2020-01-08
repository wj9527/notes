---------------------------
通用的结果集
---------------------------
	# Tuple 返回值作为通用的结果集
	
	# 同时检索多个对象记录
		List<Tuple> tuples = queryFactory.select(QUser.user, QAddress.address)
			.innerJoin(QAddress.address).on(QUser.user.id.eq(QAddress.address.user.id))
			.fetch();
		
		* 此时, Tuple 转换为数组的类型是[User, Address]
	


---------------------------
复杂结果映射为自定义对象
---------------------------
	# 通过构造函数封装为自定义的对象

		List<UserModel> userModels = queryFactory.select(Projections.constructor(UserModel.class, QUser.user.id, QUser.user.name))
					.from(QUser.user)
					.fetch();
			
		
		* 仅仅只会检索指定的字段, 对象必须要有对应的构造函数
	


		<T> ConstructorExpression<T> constructor(Class<? extends T> type, Expression<?>... exprs)
		<T> ConstructorExpression<T> constructor(Class<? extends T> type, Class<?>[] paramTypes, Expression<?>... exprs)
		<T> ConstructorExpression<T> constructor(Class<? extends T> type, Class<?>[] paramTypes, ImmutableList<Expression<?>> exprs)

		type
			* 自定义的类
		paramTypes
			* 构造函数的类型
		exprs
			* 实体的属性
		
	# 通过属性封装为自定义对象
		List<UserModel> list = queryFactory.select(Projections.bean(UserModel.class, QUser.user.name))
					.from(QUser.user)
					.fetch()
		

		* 仅仅只会检索指定的字段, 对象必须要有空的构造函数
		* 会自动根据属性名称封装属性
	

		
	
	# 封装结果为数组
		List<Integer[]> result = queryFactory.select(Projections.array(Integer[].class, QUser.user.version, QUser.user.id))
				.from(QUser.user)
				.fetch();
		
		* 多个相同类型的列, 封装为数组
	
	