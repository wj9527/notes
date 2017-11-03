------------------------
System					|
------------------------
	# 系统对象,不能创建实例,都是静态方法

------------------------
System-静态方法			|
------------------------
	void setProperty(String key,String value);
		* 设置系统环境变量
	
	void setProperties(Properties props)
		* 设置系统环境变量
	
	String getProperty(String key);
		* 从系统环境变量中读取数据
	
	String getProperty(String key, String def);
		* 从系统环境变量中读取数据
		* 如果不存在,则使用默认值
	
