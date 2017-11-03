---------------------
java.reflect.Class	 |
---------------------

---------------------
实例方法			 |
---------------------
	
	<A extends Annotation> AgetAnnotation(Class<A> annotationClass);
		* 获取指定类型的注解,如果该类未标识,则返回null
	
	Annotation[] getAnnotations();
		* 获取所该类上标识的所有注解
	
	boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) 
		* 如果指定类型的注释存在于此元素上，则返回 true，否则返回 false。 

	ClassLoader getClassLoader();
		* 获取类加载器
	
	Constructor<T> getConstructor(Class<?>... parameterTypes) 
		* 返回一个 public 修饰的 Constructor (构造器)对象 ,是根据参数来获取
	
	Constructor[] getConstructors() ;
		* 获取所有的公共构造方法对象

	Field getField(String filedName);
		* 获取指定名称的字段对象,必须是 public 修饰的,否则抛出异常

	Field getDeclaredField(String filedName);
		* 获取指定名称的字段对象,可以是任何权限修饰符修饰的
	
	Field[] getFields();
		* 返回该类所有 public 修饰的字段对象

	Field[] stsgetDeclaredFields()
		* 返回该类所有的字段对象
	
	Method getMethod(String name, Class<?>... parameterTypes);
		* 根据方法名称,以及参数描述来获取一个方法对象
	
	Method[] c.getMethods();
		* 返回的是所有的 public  方法,不包含从父类继承的

	T newInstance();
		* 使用无参构造器创建一个新的对象
	
	boolean isInterface();
		* 判断是否是接口

	boolean isEnum();
		* 判断是否是枚举
	
	boolean isArray();
		* 是否是数组

	Class<?>[] getInterfaces()
		* 获取当前类实现的所有接口
	
	InputStream getResourceAsStream(String name);
		* 非'/'开头时默认是从此类所在的包下取资源
		* 以'/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。 
	
	boolean isAssignableFrom(Class clazz);
		* 判断当前 Class,是否是 clazz 的父类,或者同类
		* 说白了,就是判断是否可以强转
	
	T[] getEnumConstants()
		* 如果此 Class 对象不表示枚举类型，则返回枚举类的元素或 null。 
		* 以声明顺序返回一个数组，该数组包含构成此 Class 对象所表示的枚举类的值，或者在此 Class 对象不表示枚举类型时返回 null


---------------------
静态方法			 |
---------------------
	Class forName(String className);
		* 加载指定的类到内存,并获取到Class对象
	