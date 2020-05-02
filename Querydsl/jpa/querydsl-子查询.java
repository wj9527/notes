-------------------
子查询
-------------------
	# 单行单列检索
		queryFactory.selectFrom(QUser.user)
				.where(QUser.user.id.eq(
					JPAExpressions.select(QUser.user.id).from(QUser.user).where(QUser.user.name.eq("KevinBlandy")))
				)
				.fetch();
		
	# 条件 exists 检索
		QUser qUser = QUser.user;
		queryFactory.select(qUser)
				.from(qUser)
				.where(JPAExpressions.selectOne().from(QUserRole.userRole)
					.where(QUserRole.userRole.userId.eq(qUser.id).and(QUserRole.userRole.roleId.eq(1)))
					.exists()
				)
				.fetch();
			
	# count检索
		QCategory qCategory = QCategory.category;
		QVideoCategory qVideoCategory = QVideoCategory.videoCategory;

		NumberExpression<Long> numberExpression = qVideoCategory.categoryId.count();
		
		QBean<CategoryDTO> categoryQBean = Projections.bean(CategoryDTO.class, 
				qCategory.id, qCategory.title, qCategory.poster, qCategory.describe, qCategory.sorted, qCategory.createdDate);
		
		
		JPAQuery<Tuple> jpaQuery = query.select(categoryQBean, 
				JPAExpressions.select(numberExpression)
					.from(qVideoCategory)
					.where(qVideoCategory.categoryId.eq(qCategory.id)))
				.from(qCategory);
		
	
	# 结果集是否为Null检索
		QPayMode qPayMode = QPayMode.payMode;
		QPayChannel qPayChannel = QPayChannel.payChannel;
	
		JPAQuery<PayModeDTO> jpaQuery = this.jpaQueryFactory.select(Projections.bean(PayModeDTO.class, qPayMode.id,
				qPayMode.name, qPayMode.enabled, qPayChannel.payModeId.isNotNull().as("selected")))
			.from(qPayMode)
			
			.leftJoin(qPayChannel).on(qPayMode.id.eq(qPayChannel.payModeId).and(qPayChannel.goodsId.eq(goodsId)))
			
			.where(qPayMode.deletedDate.eq(SystemProperties.NOT_DELETED).and(qPayMode.payTypeId.eq(1)))
			.orderBy(new OrderSpecifier<>(Order.DESC, qPayMode.sorted), new OrderSpecifier<>(Order.DESC, qPayMode.createdDate));
		
		List<PayModeDTO> payModes = jpaQuery.fetch();
		

	# 结果集子查询
		//TODO
	
