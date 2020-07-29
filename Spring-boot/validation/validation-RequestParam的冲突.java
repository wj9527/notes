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
	

	
	# �½���֤ע��
		* �½�һ��ע��ȽϺ�
			import static java.lang.annotation.RetentionPolicy.RUNTIME;
			import java.lang.annotation.ElementType;
			import java.lang.annotation.Retention;
			import java.lang.annotation.Target;

			import javax.validation.Constraint;
			import javax.validation.Payload;
			import javax.validation.ReportAsSingleViolation;

			import org.hibernate.validator.constraints.CompositionType;
			import org.hibernate.validator.constraints.ConstraintComposition;
			import org.hibernate.validator.constraints.Length;


			@Retention(RUNTIME)
			@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
			@Constraint(validatedBy = {})
			@ReportAsSingleViolation
			@Phone							// ָ���ֻ�������֤
			@Length(max = 0, min = 0)		// ����Ϊ���ա��ַ���
			@ConstraintComposition(CompositionType.OR)	// or��ϵ
			public @interface PhoneOrEmpty {
				String message() default "�ֻ����벻��ȷ��ֻ֧�ִ�½�ֻ�����";
				Class<?>[] groups() default {};
				Class<? extends Payload>[] payload() default {};
			}