--------------------
聚合检索
--------------------
	# 聚合检索
		List<Tuple> tuples = queryFactory.select(QUser.user.name,
							QUser.user.id.max(),
							QUser.user.id.avg())
				.from(QUser.user).groupBy(QUser.user.name)
				.fetch();

		for (Tuple tuple : tuples){

			// 依次读取属性			
			String name = tuple.get(0, String.class);
			Number max = tuple.get(1, Number.class);
			Number avg = tuple.get(2, Number.class);


			// 转换为数组
			Object[] values = tuple.toArray();
		}


		// SQL
		select
			user0_.name as col_0_0_,
			max(user0_.id) as col_1_0_,
			avg(user0_.id) as col_2_0_ 
		from
			user user0_ 
		group by
			user0_.name
		

