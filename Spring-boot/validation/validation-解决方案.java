---------------------------------------------------------------------
@RequestParam(required = false) �ĳ�ͻ
---------------------------------------------------------------------
	# �ֻ�������֤ע��
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
			String message() default "�ֻ����벻��ȷ��ֻ֧�ִ�½�ֻ�����";
			Class<?>[] groups() default {};
			Class<? extends Payload>[] payload() default {};
		}


	# ������֤������
		@RequestParam(value = "phone", required = false) @Phone String phone
		/api?phone=  //���׳��쳣������phone����������Ϊ���ַ�����
	
	# �Զ�����֤ע��
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
			String message() default "ֻ���ǿ�";
			Class<?>[] groups() default {};
			Class<? extends Payload>[] payload() default {};
		}
	
		*  ��֤ʵ���࣬ר����֤String��Collection
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


	
	# �½���֤ע��
		* �½�һ��ע��ȽϺ�

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
		@Pattern(regexp = RegexPatterns.PHONE_NUMBER)	// �ֻ�������֤����
		@MustEmpty			// ����Ϊ���ա�
		@ConstraintComposition(CompositionType.OR)		// ��ϵ��or��ֻҪ����һ�����㼴��
		public @interface PhoneOrEmpty {
			String message() default "�ֻ����벻��ȷ��ֻ֧�ִ�½�ֻ�����";
			Class<?>[] groups() default {};
			Class<? extends Payload>[] payload() default {};
		}
