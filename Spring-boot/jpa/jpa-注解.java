--------------------
JPA的注解			|
--------------------
	@EnableJpaRepositories
	@EntityScan
	@NoRepositoryBean
		* 标识在 Repository 上,表示该接口不是一个Repository
		* 不需要生成动态代理对象

