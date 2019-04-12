--------------------
JPA的注解			|
--------------------
	@EnableJpaRepositories
	@EntityScan
	@NoRepositoryBean
		* 标识在 Repository 上,表示该接口不是一个Repository
		* 不需要生成动态代理对象

--------------------
Entity注解			|
--------------------
	@Entity
	@Table
	@Column
	@Id
	@GeneratedValue
		* 标识ID字段,并且指定其生成策略
		* strategy ,指定生成策略
			GenerationType.TABLE		使用一个特定的数据库表格来保存主键。 
			GenerationType.SEQUENCE		根据底层数据库的序列来生成主键,条件是数据库支持序列
			GenerationType.IDENTITY		主键由数据库自动生成(主要是自动增长型）)
			GenerationType.AUTO			主键由程序控制