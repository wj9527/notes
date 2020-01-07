---------------------
关联关系
---------------------
	@JoinColumn
		String name() default "";
			* 外键列的名称
		String referencedColumnName() default "";
			* 当前实体的字段, 非必填, 默认当表的id
		boolean unique() default false;
			* 外键是否唯一
		boolean nullable() default true;
			* 是否可以为null
		boolean insertable() default true;
			* 是否跟随一起新增
		boolean updatable() default true;
			* 是否跟随一起修改
		String columnDefinition() default "";
			* 列约束
		String table() default "";
		ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);

		* 它要配合 @OneToOne,@OneToMany,@ManyToOne,@ManyToMany 使用, 不然没意义

	@JoinColumns
		JoinColumn[] value();
		ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);

		* 用于同时定义多个 @JoinColumn
	
	@JoinTable
		String name() default "";
			* 中间表的名称

		String catalog() default "";
		String schema() default "";

		JoinColumn[] joinColumns() default {};
			* 当前对象在中间表中的外键信息
				@JoinColumn
					|-name					中间表中, 当前对象的id字段名称
					|-referencedColumnName	当前对象的id字段名称


		JoinColumn[] inverseJoinColumns() default {};
			* 对方对象在中间表中的外键信息
				@JoinColumn
					|-name					中间表中, 对方对象的id字段名称
					|-referencedColumnName	对方对象的id字段名称

		ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
			@ForeignKey
				|-String name()
				|-ConstraintMode value() default CONSTRAINT;
					CONSTRAINT
					NO_CONSTRAINT
					PROVIDER_DEFAULT
				|-String foreignKeyDefinition() default "";

		ForeignKey inverseForeignKey() default @ForeignKey(PROVIDER_DEFAULT);

		UniqueConstraint[] uniqueConstraints() default {};
			@UniqueConstraint
				|-String name() default "";
				|-String[] columnNames();

		Index[] indexes() default {};
			@Index
				|-String name()
				|-String columnList();
				|-boolean unique() default false;
		
	@OrderBy
		String value() default "";
