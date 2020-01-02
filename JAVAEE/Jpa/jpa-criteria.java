------------------
criteriaQuery
------------------
	# 核心的接口
		CriteriaQuery
		Root
	
	
	# 检索所有
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		List<User> users = entityManager.createQuery(criteriaQuery).getResultList();

		select
			user0_.id as id1_0_,
			user0_.create_date as create_d2_0_,
			user0_.version as version3_0_,
			user0_.name as name4_0_ 
		from
			user user0_

	# 多个条件关系
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		// 设置条件之间的一组关系, 关系是or
		Predicate p1 = criteriaBuilder.or(criteriaBuilder.lt(root.get(User_.id), 1), criteriaBuilder.like(root.get(User_.name), "K"));
		// 设置条件之前的一组关系, 关系是and
		Predicate p2 = criteriaBuilder.and(p1, criteriaBuilder.lt(root.get(User_.id), 2));
		List<User> users = entityManager.createQuery(criteriaQuery.where(p2)).getResultList();

		select
			user0_.id as id1_0_,
			user0_.create_date as create_d2_0_,
			user0_.version as version3_0_,
			user0_.name as name4_0_ 
		from
			user user0_ 
		where
			(
				user0_.id<1 
					or 
				user0_.name like ?
			) 
		and 
			user0_.id<2
	
	
	# COUNT 统计查询
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<User> root = criteriaQuery.from(User.class);
		Expression<Long> expression = criteriaBuilder.count(root);
		criteriaQuery.select(expression);
		Long count = entityManager.createQuery(criteriaQuery).getSingleResult();
	
	# 检索单个字段
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root.get(User_.name));
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));
		String result = entityManager.createQuery(criteriaQuery).getSingleResult();
	
	# 检索多个字段
		

	# 子查询
