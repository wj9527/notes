-------------------
子查询
-------------------
	# 条件子查询
		queryFactory.selectFrom(QUser.user)
				.where(QUser.user.id.eq(
					JPAExpressions.select(QUser.user.id).from(QUser.user).where(QUser.user.name.eq("KevinBlandy")))
				)
				.fetch();
		

	
	# 结果集子查询
		//TODO
	
