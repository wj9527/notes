-----------------------------
一对一的关系
-----------------------------
	# 用户

	# 角色


-----------------------------
一对一的关系注解
-----------------------------
	@OneToOne
		Class targetEntity() default void.class;
		CascadeType[] cascade() default {};
			* 级联操作策略, 枚举
				ALL				关联所有操作
				PERSIST			级联保存
				MERGE			级联更新
				REMOVE			级联删除
				REFRESH			级联刷新
				DETACH			级联分离
			* 默认为空, 不会产生关联操作
				
		FetchType fetch() default EAGER;
			* 关联数据的获取方式, 枚举
				LAZY	延迟加载
				EAGER	立即加载

		boolean optional() default true;
			* 是否允许为空

		String mappedBy() default "";
			* 关联关系被谁维护, 一般不用填写该字段, 表示当前实体在维护
			* 如果要放弃维护权, 则该属性指的是, 另一方的实体里面 @JoinColumn 或者 @JoinTable 的属性字段名称, 而不是数据库字段, 也不是实体对象的名称

			* 只有关系维护方才能操作两者关系, 被维护方即使设置了维护方属性进行存储也不会更新外键关联(避免级联保存的时候, 多一个update操作)
			* 当使用了该注解的时候, 该字段不能添加 @JoinColumn 或者 @JoinTable (不能同时使用)

		boolean orphanRemoval() default false;
			* 是否级联删除, 跟 cascade = CascadeType.REMOVE 效果一样
			* 只要是配置了两种类型中的其中一个, 都会被级联删除
		
		* 一对一的映射关系, 可以双向关联, 也可以只配置一方

-----------------------------
一对一的保存
-----------------------------

-----------------------------
一对一的删除
-----------------------------
		

