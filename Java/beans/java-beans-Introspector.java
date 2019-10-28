------------------------
Introspector			|
------------------------
	# Java 内省, 提供N多的静态方法

	# 静态方法
		String decapitalize(String name)
		void flushCaches()
		void flushFromCaches(Class<?> clz)

		BeanInfo getBeanInfo(Class<?> beanClass)
		BeanInfo getBeanInfo(Class<?> beanClass, int flags)
		BeanInfo getBeanInfo(Class<?> beanClass, Class<?> stopClass)

		String[] getBeanInfoSearchPath()
		void setBeanInfoSearchPath(String[] path)

