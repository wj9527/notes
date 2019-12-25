----------------
JPQL检索
----------------
	# 参数的绑定
		* 通过位置绑定	WHERE id = ?1
		* 通过名称绑定	WHERE id = :id
	
----------------
JPQL检索语法
----------------
	# 语法
		* 操作的是对象 @Entity, 不是表, 操作的是对象属性, 也不是字段
		* 默认Entity就是类名称大写

		* 不支持使用 `` 符号

	# 基本
		FROM User u WHERE u.id = :id
	
	# LIKE
		FROM User u WHERE u.name LIKE %:name%
	
	# IN
		FROM User u WHERE u.id in :ids

		* ids是一个集合
	
	# ORDER BY
		FROM User u WHERE u.id = :id ORDER BY id DESC
	
	
----------------
SPEL
----------------
	# 支持使用spel表达式进行对象属性的导航
		@Query(value = "FROM user u WHERE u.id = :#{#user.id}")
		Page<UserEntity> testSelect (@Param("user")UserEntity userEntity, Pageable pageable);
	
	# 支持使用SPEL表达式读取某些特殊的变量
		@Entity
		class User

		@Query("select u from #{#entityName} u where u.lastname = ?1")
		List<User> findByLastname(String lastname);

		#{#entityName}	
			* 读取到entity实体的名称, 默认是实体名称的小写
			* 也可以在 @Entity(name = "user")中, 通过name属性设置

----------------
JPQL检索注解
----------------
	# Query
		String value() default "";
			* 执行语句, 可以是CREUD, 可以是本地SQL, JPQL

		String countQuery() default "";
			* 查询总数量的语句
			* 方法最后一个参数是 Pageable , 返回值是: Page<T>
				@Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1",
					countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
					nativeQuery = true)
				Page<User> findByLastname(String lastname, Pageable pageable);

		String countProjection() default "";
		boolean nativeQuery() default false;
			* 是否是本地SQL查询

		String name() default "";
		String countName() default "";

	
	# @Param
		* 通过命名参数的绑定 , 可以忽略参数的位置
			@Query(value = "select * from #{#entityName} where id > :id", nativeQuery = true)
			Page<UserEntity> testSelect (@Param("id")Integer id, Pageable pageable);

		* 如果在JDK8中保留了参数名称, 可以省略该注解
	
	
	# @Modifying
		boolean flushAutomatically() default false;
		boolean clearAutomatically() default false;

		* 和 @Query 组合使用
		* 标识在某个 repository 的方法上,表示当前的 @Query 是一个UPDATE 语句
		* 该方法返回的 int 值标签受到影响的行数

	# @NamedQuery
		String name();
		String query();
		LockModeType lockMode() default NONE;
		QueryHint[] hints() default {};

		* 标识在 Entity 上
		* name	Sring类型的属性,用于指定检索名,例如 "User.findByName"
		* query	String类书的属性,用于HQL,例如 "FROM User WHERE name = :name"
		* 在该 Entity 的接口中定义的 findByName 方法,就是通过 query 属性的HQL来进行检索的
	
	# @QueryHint
		 String name(); 
		 String value();
		

	# @QueryHints
		QueryHint[] value() default {};
		boolean forCounting() default true;