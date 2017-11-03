
---------------------
java.reflect.Method	 |
---------------------

---------------------
实例方法			 |
---------------------
	invoke(Object obj,Object value...);
		* 以反射的方法执行方法对象,第一个参数表示是哪个对象的此方法,第二个参数表示方法参数,如果没有直接无视即可

	<T extends Annotation>  getAnnotation(Class<T> annotationClass) 
		* 如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null。 

	Annotation[] getDeclaredAnnotations();  
		* 获取该方法所有的注解

	boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);
		* 判断是否有指定类型的注解标识在该方法