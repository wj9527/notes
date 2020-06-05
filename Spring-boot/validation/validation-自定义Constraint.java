--------------------------
自定义Constraint
--------------------------
	# 内置的constraint，对于实际使用来说是根本不够用

	# 定制一个constraint需要两个部分，一个是constraint注解，一个是执行校验逻辑的类


	# 实现接口 ConstraintValidator<A extends Annotation, T> 完成验证逻辑
		* 泛型 A 是自己定义的校验注解
		* T 是校验目标的数据类型
			import javax.validation.ConstraintValidator;
			import javax.validation.ConstraintValidatorContext;
			public class TestConstraintValidator implements ConstraintValidator<TestConstraint, String> {
				@Override
				public void initialize(TestConstraint constraintAnnotation) {
					// 构造方法可以获取到注解
				}
				@Override
				public boolean isValid(String value, ConstraintValidatorContext context) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate("这里可以重新覆写注解中的错误提示消息").addConstraintViolation();
					return "Test".equals(value);
				}
			}

		

	# 自定义注解
		* 必须是运行时 @Retention(RUNTIME)
		* 在注解上标识 @Constraint 注解, 并且指定 ConstraintValidator 接口的实现类
			import static java.lang.annotation.ElementType.FIELD;
			import static java.lang.annotation.RetentionPolicy.RUNTIME;
			import java.lang.annotation.Retention;
			import java.lang.annotation.Target;
			import javax.validation.Constraint;
			import javax.validation.Payload;

			@Retention(RUNTIME)
			@Target(FIELD)
			@Constraint(validatedBy = { TestConstraintValidator.class }) // 指定验证的实现类
			public @interface TestConstraint {
				
				// 设置异常时的提示信息
				String message() default "字段值不是Test";

				// group
				Class<?>[] groups() default {};

				// payload
				Class<? extends Payload>[] payload() default {};
			}
		
		* @Constraint 的唯一属性 validatedBy 是一个数组
			Class<? extends ConstraintValidator<?, ?>>[] validatedBy();
		* 如果这个注解需要校验多种数据类型, 那么就实现多个 ConstraintValidator 接口, 并都通过 validatedBy 标识
		
		* message(), groups(), payload() 是必要的属性

--------------------------
组合 Constraint
--------------------------
	# 利用现有的constraint注解的功能，实现继承校验规则	

	# 组合
		import static java.lang.annotation.ElementType.FIELD;
		import static java.lang.annotation.RetentionPolicy.RUNTIME;

		import java.lang.annotation.Retention;
		import java.lang.annotation.Target;

		import javax.validation.Constraint;
		import javax.validation.OverridesAttribute;
		import javax.validation.Payload;
		import javax.validation.constraints.Max;
		import javax.validation.constraints.Min;

		@Retention(RUNTIME)
		@Target(FIELD)
		@Constraint(validatedBy = { })

		/**
		 * 一个或者多个验证注解的组合
		 */
		@Min(0)
		@Max(value = 100)
		public @interface Range {

			/**
			 * 可以通过  @OverridesAttribute 注解覆写上面声明注解中的指定属性
			 */
			@OverridesAttribute(constraint = Min.class, name = "value") long min() default 1;
			@OverridesAttribute(constraint = Max.class, name = "value") long max() default 5;
			
			@OverridesAttribute(constraint = Min.class, name = "message") String minMessage() default "最小只能是1";
			@OverridesAttribute(constraint = Max.class, name = "message") String maxMessage() default "最大只能是5";
			
			String message() default "区间值必须在 1 - 5 之间";

			Class<?>[] groups() default { };

			Class<? extends Payload>[] payload() default { };
		}

		* 自定义注解, 在注解上标识N个验证注解

		* message(), groups(), payload() 是必要的属性
		* @Constraint(validatedBy = { }) 注解是必须的，哪怕没有指定 validatedBy

		* 可以在注解内, 通过 @OverridesAttribute 来覆写指定注解的指定属性
			@OverridesAttribute
				Class<? extends Annotation> constraint();
					* 指定注解
				String name() default "";
					* 指定注解属性
				int constraintIndex() default -1;
					* 如果是重复标识的多个相同注解
					* 通过此属性, 指定注解的下标
		* 可以通过定义多个 @OverridesAttribute 注解，来覆写一个顶部注解中的多个属性
		

		* @ReportAsSingleViolation 返回单一验证信息
			* 使用了组合约束注解，默认情况下会返回组合中各个约束的错误信息
			* 如果希望该注解仅返回一条验证失败信息，可以使用这个注解，会在验证失败后返回 message() 的默认信息。
	
		* @ConstraintComposition 来表示组合注解中的关系
			CompositionType value() default AND;
				* 枚举
					OR
					AND
					ALL_FALSE
