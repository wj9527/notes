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
				public boolean isValid(String value, ConstraintValidatorContext context) {
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


--------------------------
组合 Constraint
--------------------------
