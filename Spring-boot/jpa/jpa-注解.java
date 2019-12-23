--------------------
JPA的注解			|
--------------------
	@EnableJpaRepositories
	@EntityScan
	@NoRepositoryBean
		* 标识在 Repository 上,表示该接口不是一个Repository
		* 不需要生成动态代理对象

	@Query
		* 标识在某个 repository 的方法上,用于定义HQL语句
		* 可以是 INSERT,CREATE,UPDATE,DELETE 语句

	@Modifying
		* 和 @Query 组合使用
		* 标识在某个 repository 的方法上,表示当前的 @Query 是一个UPDATE 语句
		* 该方法返回的 int 值标签受到影响的行数

	@NamedQuery
		* 标识在 Entity 上
		* name	Sring类型的属性,用于指定检索名,例如 "User.findByName"
		* query	String类书的属性,用于HQL,例如 "FROM User WHERE name = :name"
		* 在该 Entity 的接口中定义的 findByName 方法,就是通过 query 属性的HQL来进行检索的

	