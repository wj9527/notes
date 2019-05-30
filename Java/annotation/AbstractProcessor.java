------------------------
AbstractProcessor		|
------------------------
	# JDK6提供的一个抽象类
		* 有一个抽象方法
			public abstract boolean process(Set<? extends TypeElement> annotations,   RoundEnvironment roundEnv);
	
		* 可以在编译的时候, 执行自定义的代码
