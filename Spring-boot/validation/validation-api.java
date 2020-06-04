-----------------------
Validation
-----------------------
	# 静态方法
		static ValidatorFactory buildDefaultValidatorFactory()
		static GenericBootstrap byDefaultProvider()
		static <T extends Configuration<T>, U extends ValidationProvider<T>> ProviderSpecificBootstrap<T> byProvider(Class<U> providerType)

-----------------------
ValidatorFactory
-----------------------
	# 接口方法
		Validator getValidator();
		ValidatorContext usingContext();
		MessageInterpolator getMessageInterpolator();
		TraversableResolver getTraversableResolver();
		ConstraintValidatorFactory getConstraintValidatorFactory();
		ParameterNameProvider getParameterNameProvider();
		ClockProvider getClockProvider();
		public <T> T unwrap(Class<T> type);
		public void close();

-----------------------
Validator
-----------------------
	# 接口方法
		<T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups);
		<T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups);
		<T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups);
		BeanDescriptor getConstraintsForClass(Class<?> clazz);
		<T> T unwrap(Class<T> type);
		ExecutableValidator forExecutables();

-----------------------
ConstraintViolation
-----------------------
	# 接口方法
		String getMessage();
		String getMessageTemplate();
		T getRootBean();
		Class<T> getRootBeanClass();
		Object getLeafBean();
		Object[] getExecutableParameters();
		Object getExecutableReturnValue();
		Path getPropertyPath();
		Object getInvalidValue();
		ConstraintDescriptor<?> getConstraintDescriptor();
		<U> U unwrap(Class<U> type);