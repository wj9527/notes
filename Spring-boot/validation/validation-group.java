----------------------------------
Group
----------------------------------
	# 分组的概念
		* 可以用groups来实现:同一个模型在不同场景下，(动态区分)校验模型中的不同字段。
	
	# 所有的校验注解都有一个属性
		Class<?>[] groups() default { };

		* 用于指定一个或者多个Group
	
	# 默认的 Group 接口
		package javax.validation.groups
		public interface Default {
		}
		
		* 这是一个标记接口，默认的情况下，校验注解没有声明 group 属性，那么该注解就是属于这个 default group 的
	
	# 自定义Group
		* 随便定义类, 无所谓实现不实现 Group 接口

			例如: @Validated(value = Create.class)

			1. Create 继承了Default，那么@Validated(value = Create.class)的校验范畴就为【Create】和【Default】
			2. Create 没继承Default，那么@Validated(value = Create.class)的校验范畴只为【Create】，而@Validated(value = {Create.class, Default.class})的校验范畴才为【Create】和【Default】。
		

	
	# 在声明注解的时候，指定Group
		@NotNull(message = "ID不能为空", groups = { UpdateGroup.class })
		public Integer id;
	
	# 校验的时候，指定Group
		validation.validate(user, UpdateGroup.class);