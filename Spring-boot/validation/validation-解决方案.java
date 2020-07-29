---------------------------------------------------------------------
@RequestParam(required = false) 的冲突
---------------------------------------------------------------------
	# 手机号码验证注解
		import java.lang.annotation.ElementType;
		import java.lang.annotation.Retention;
		import java.lang.annotation.Target;

		import javax.validation.Constraint;
		import javax.validation.Payload;
		import javax.validation.ReportAsSingleViolation;
		import javax.validation.constraints.Pattern;

		import io.streamer.common.constant.RegexPatterns;

		@Retention(RUNTIME)
		@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
		@Constraint(validatedBy = {})
		@ReportAsSingleViolation
		@Pattern(regexp = RegexPatterns.PHONE_NUMBER)
		public @interface Phone {
			String message() default "手机号码不正确，只支持大陆手机号码";
			Class<?>[] groups() default {};
			Class<? extends Payload>[] payload() default {};
		}


	# 参数验证的问题
		@RequestParam(value = "phone", required = false) @Phone String phone
		/api?phone=  //会抛出异常，但是phone参数允许是为空字符串的
	
	# 自定义验证注解
		import static java.lang.annotation.RetentionPolicy.RUNTIME;
		import java.lang.annotation.ElementType;
		import java.lang.annotation.Retention;
		import java.lang.annotation.Target;

		import javax.validation.Constraint;
		import javax.validation.Payload;

		import io.streamer.common.validate.validator.CollectionMustEmptyValidator;
		import io.streamer.common.validate.validator.StringMustEmptyValidator;


		@Retention(RUNTIME)
		@Target(value = { ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
		@Constraint(validatedBy = { StringMustEmptyValidator.class, CollectionMustEmptyValidator.class })
		public @interface MustEmpty {
			String message() default "只能是空";
			Class<?>[] groups() default {};
			Class<? extends Payload>[] payload() default {};
		}
	
		*  验证实现类，专门验证String和Collection
			import javax.validation.ConstraintValidator;
			import javax.validation.ConstraintValidatorContext;

			import io.streamer.common.validate.constraints.MustEmpty;

			public class StringMustEmptyValidator implements ConstraintValidator<MustEmpty, String> {

				@Override
				public boolean isValid(String value, ConstraintValidatorContext context) {
					return value != null && value.isEmpty();
				}
			}

			import java.util.Collection;
			import javax.validation.ConstraintValidator;
			import javax.validation.ConstraintValidatorContext;

			import io.streamer.common.validate.constraints.MustEmpty;

			public class CollectionMustEmptyValidator implements ConstraintValidator<MustEmpty, Collection<?>> {

				@Override
				public boolean isValid(Collection<?> value, ConstraintValidatorContext context) {
					return value != null && value.isEmpty();
				}
			}


	
	# 新建验证注解
		* 新建一个注解比较好

		import java.lang.annotation.ElementType;
		import java.lang.annotation.Retention;
		import java.lang.annotation.Target;

		import javax.validation.Constraint;
		import javax.validation.Payload;
		import javax.validation.ReportAsSingleViolation;
		import javax.validation.constraints.Pattern;

		import org.hibernate.validator.constraints.CompositionType;
		import org.hibernate.validator.constraints.ConstraintComposition;

		import io.streamer.common.constant.RegexPatterns;

		@Retention(RUNTIME)
		@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
		@Constraint(validatedBy = {})
		@ReportAsSingleViolation
		@Pattern(regexp = RegexPatterns.PHONE_NUMBER)	// 手机号码验证正则
		@MustEmpty			// 必须为“空”
		@ConstraintComposition(CompositionType.OR)		// 关系是or，只要其中一个满足即可
		public @interface PhoneOrEmpty {
			String message() default "手机号码不正确，只支持大陆手机号码";
			Class<?>[] groups() default {};
			Class<? extends Payload>[] payload() default {};
		}
