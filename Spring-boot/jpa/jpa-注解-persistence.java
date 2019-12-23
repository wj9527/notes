# 注解所在包: javax.persistence

@Entity
	* 标识当前类是一个被DB管理的实体类

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
		* 设置列类型以及约束, 例如: columnDefinition="timestamp", columnDefinition="varchar(100)" 
		* 还可以通过它来设置列注释: columnDefinition="bigint COMMENT '主键，自动生成'"

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