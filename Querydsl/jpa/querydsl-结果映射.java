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
		* 会自动根据属性名称封装属性, 如果属性名称不符合, 可以使用别名
			QUser.user.name.as("name")
		

		* 多表结果集的情况下, 使用 Projections.bean 封装部分字段, 必须要声明出需要检索的列(属性), 不能直接写一个Q查询对象, 不能没法查询出东西

			// 明星表
            QStar qStar = QStar.star;
            
            // 明星关联的视频表
            QVideoStar qVideoStar = QVideoStar.videoStar;
            
            // 把部分明星数据封装为 StartDTO 对象
            QBean<StarDTO> qBean = Projections.bean(StarDTO.class, qStar.id, qStar.name, qStar);
			// QBean<StarDTO> qBean = Projections.bean(StarDTO.class, qStar);	错误
            
            JPAQuery<Tuple> jpaQuery = jpaQueryFactory.select(qBean,
                        JPAExpressions.select(qVideoStar.starId.count()).from(qVideoStar).where(qVideoStar.starId.eq(qStar.id)))
                    .from(qStar);

            QueryResults<Tuple> queryResults = jpaQuery.fetchResults();
            List<StarDTO> stars = queryResults.getResults().stream().map(i -> {
            	
            	// 获取明星记录数据
            	StarDTO starDTO = i.get(qBean);
            	
            	// 获取子查询统计数据
                starDTO.setVideoCount(i.get(1, Long.class).intValue());
                
                return starDTO;
            }).collect(Collectors.toList());

		
		* Qbean 可以有多个
			QOrder qOrder = QOrder.order;
			QUser qUser = QUser.user;
			
			// 封装订单信息
			QBean<OrderDTO> orderQBean = Projections.bean(OrderDTO.class, qOrder.id, qOrder.amount, qOrder.orderNumber,
											qOrder.createdDate, qOrder.paymentDate, qOrder.snapshot, qOrder.state);
			
			// 封装用户信息
			QBean<User> userQBean = Projections.bean(User.class, qUser.account, qUser.id, qUser.avatar, qUser.name);
			
			JPAQuery<Tuple> jpaQuery = jpaQueryFactory.select(orderQBean, userQBean)
				.from(qOrder)
				.innerJoin(qUser).on(qOrder.userId.eq(qUser.id))
				.orderBy(qOrder.createdDate.desc());
			
			QueryResults<Tuple> queryResults = jpaQuery.fetchResults();
			List<OrderDTO> results = queryResults.getResults().stream().map(i -> {
				// 从结果集Tuple中根据Qbean获取到数据
				OrderDTO orderDTO = i.get(orderQBean);
				orderDTO.setUser(i.get(userQBean));
				return orderDTO;
			}).collect(Collectors.toList());

		
	
	# 封装结果为数组
		List<Integer[]> result = queryFactory.select(Projections.array(Integer[].class, QUser.user.version, QUser.user.id))
				.from(QUser.user)
				.fetch();
		
		* 多个相同类型的列, 封装为数组
	




---------------------------
对结果集进行group
---------------------------
	# 也就是关联检索结果集的封装

