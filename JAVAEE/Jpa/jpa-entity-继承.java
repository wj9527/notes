---------------------
继承关系
---------------------
	@MappedSuperclass
		* 标识在实体类的父类, 用于JPA注解的继承
		* 一般用于抽象类, 抽取出所有实体类公用的字段
		* 使用该注解标识的类, 不能再用  @Entity 注解标识

	@AttributeOverride
		String name();
		Column column();
	
	@AssociationOverride
		String name();
		JoinColumn[] joinColumns() default {};
		ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
		JoinTable joinTable() default @JoinTable;

---------------------
继承策略
---------------------
	@Inheritance
		* 指定实体的继承策略
			InheritanceType strategy() default SINGLE_TABLE;
				SINGLE_TABLE			所有继承的实体都保存在同一张数据库表中
				TABLE_PER_CLASS			有继承关系的所有实体类都将保存在单独的表中
				JOINED					每个实体子类都将保存在一个单独的表中
		
	
	* 一般用于指定 entity 与 entity 之间的继承关系
