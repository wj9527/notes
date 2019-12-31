---------------------
annotation
---------------------
	# 注解所在包: javax.persistence

---------------------
一般注解
---------------------
	@Entity
		* 表示类, 被EntityManager管理
		String name() default "";
			* 实体的名称, 可以用于JPQL检索
			* 默认是当前类名

	@Table
		String name() default "";
			* 数据表的名称

		String catalog() default "";

		String schema() default "";

		UniqueConstraint[] uniqueConstraints() default {};

		Index[] indexes() default {};
			* 表索引的设置
			* Index 注解的属性
				String name				索引名称
				String columnList		索引的列
				boolean unique			是否唯一约束

	@Column
		String name() default "";
			* 字段的名称, 如果和属性名称一样, 可以省略

		boolean unique() default false;

		boolean nullable() default true;
			* 是否可以为null

		boolean insertable() default true;

		boolean updatable() default true;

		String columnDefinition() default "";
			* 设置列类型以及约束以及注释, 例如: 
				columnDefinition = "int(20) unsigned COMMENT 'id'"
				columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'"

		String table() default "";

		int length() default 255;

		int precision() default 0;

		int scale() default 0;

	@Id
	@GeneratedValue
		* 标识ID字段,并且指定其生成策略
		* strategy ,指定生成策略
			GenerationType.TABLE		使用一个特定的数据库表格来保存主键。 
			GenerationType.SEQUENCE		根据底层数据库的序列来生成主键,条件是数据库支持序列
			GenerationType.IDENTITY		主键由数据库自动生成(主要是自动增长型）)
			GenerationType.AUTO			主键由程序控制

	@MappedSuperclass
		* 标识在实体类的父类, 用于JPA注解的继承、

	@Transient
		* 表示该字段不是数据表的映射字段

	@Version
		* 标识版本号字段


---------------------
生命周期相关的注解
---------------------
	@PrePersist
	@PostPersist

		* 在save前后后调用
	
	@PreUpdate
	@PostUpdate 
		* 在修改前后调用
	
	@PreRemove 
	@PostRemove 
		* 在删除前后调用

	@EntityListeners
		* 指定外部周期的实现类
	
	@PostLoad
		* entity被映射(find, load....)之后调用
	
