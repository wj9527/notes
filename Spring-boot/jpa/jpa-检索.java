-----------------------------
检索的方式					 |
----------------------------
	# JPA父接口定义的检索方法
	# 方法命名规则检索
	# JPQL检索
	# 本地SQL检索
	# Specification 动态查询

-----------------------------
JPA父接口定义的检索方法		 |
----------------------------

    List<UserEntity> findAllById(Iterable<Integer> integers);
    Optional<UserEntity> findById(Integer integer);
	UserEntity getOne(Integer integer);
	
	Optional<UserEntity> findOne(Specification<UserEntity> spec);
    <S extends UserEntity> Optional<S> findOne(Example<S> example);

	List<UserEntity> findAll();
    List<UserEntity> findAll(Sort sort);
    Page<UserEntity> findAll(Pageable pageable);

	<S extends UserEntity> List<S> findAll(Example<S> example);
    <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort);
    <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable);

    List<UserEntity> findAll(Specification<UserEntity> spec);
    Page<UserEntity> findAll(Specification<UserEntity> spec, Pageable pageable);
    List<UserEntity> findAll(Specification<UserEntity> spec, Sort sort);
	
	long count();
	<S extends UserEntity> long count(Example<S> example);
    long count(Specification<UserEntity> spec); 

	<S extends UserEntity> boolean exists(Example<S> example);
	boolean existsById(Integer integer);
    
	
	* 无非就是, 分页, Specification动态条件, Example条件, Sort 各种组合查询
	* findOne 是立即加载, getOne 是延迟加载


-----------------------------
排序						 |
-----------------------------
	# 类库
		Sort
			|-JpaSort
		Sort.Order
		Sort.Direction

		* 一个Sort对象包含N多个 Order 对象
	
	# Sort.Order
		Order(@Nullable Direction direction, String property)
		Order(@Nullable Direction direction, String property, NullHandling nullHandlingHint)
		
		direction
			* 排序策略,枚举
				ASC
				DESC
		property
			* 排序字段名称
		nullHandlingHint	
			* 对于空值的排序处理,枚举
				NATIVE				让DB决定
				NULLS_FIRST			排在最前面
				NULLS_LAST			排在最后面
	
	# demo
		// 根据name升序排序
		Sort nameAes = Sort.by(new Sort.Order(Sort.Direction.ASC,"name"));

		// 根据age降序,createDate升序 排序
        Sort ageDesccreateDateAes = Sort.by(Sort.Order.desc("age"),Sort.Order.asc("createDate"));

		// 根据gender排序,如果gender字段是空的,就排在记录的最前面
        Sort genderAes = Sort.by(new Sort.Order(Sort.Direction.ASC,"gender",Sort.NullHandling.NULLS_FIRST));

		// 不排序
		Sort unsorted = Sort.unsorted();
