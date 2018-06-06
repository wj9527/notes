----------------------------
枚举处理					|
----------------------------
	# 默认的枚举处理是直接把枚举的名称序列化为字符串


----------------------------
把枚举转换为一个对象		|
----------------------------
	
		//创建序列化配置
		SerializeConfig serializeConfig = new SerializeConfig();
		//使用该 api 来指定枚举类.class,可变参数
		serializeConfig.configEnumAsJavaBean(PreservationModel.Category.class);
		

		//创建fastjson配置
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializeConfig(serializeConfig);
		
		PreservationModel preservationModel = new PreservationModel();
		preservationModel.setCategory(PreservationModel.Category.BASIS);
		preservationModel.setId(1);
		
		//在序列化对象时候,设置fastjsonConfig或者serializeConfig
		System.out.println(JSON.toJSONString(preservationModel, serializeConfig));
		