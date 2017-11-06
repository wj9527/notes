----------------------------
RestTemplate				|
----------------------------
	* Spring RestTemplate 是 Spring 提供的用于访问 Rest 服务的客户端
	* RestTemplate 提供了多种便捷访问远程Http服务的方法,能够大大提高客户端的编写效率
	* 很客户端比如 Android或者第三方服务商都是使用 RestTemplate 请求 restful 服务
	* 参考资料
		http://www.jianshu.com/p/c9644755dd5e

----------------------------
RestTemplate-实例创建		|
----------------------------
	# 直接创建
		new RestTemplate()
	
	# 工厂创建
		new RestTemplateBuilder().build();
	

----------------------------
RestTemplate-api			|
----------------------------
	# 属性设置相关
		public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters)
			* 设置HTTP消息解析器
		
		public List<HttpMessageConverter<?>> getMessageConverters()
			* 获取默认的HTTP消息解析器
			* 默认注册
				org.springframework.http.converter.ByteArrayHttpMessageConverter
				org.springframework.http.converter.StringHttpMessageConverter
				org.springframework.http.converter.ResourceHttpMessageConverter
				org.springframework.http.converter.xml.SourceHttpMessageConverter
				org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter
				org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter
				org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
	
	# HTTP请求
		

----------------------------
RestTemplate-POST			|
----------------------------
	RestTemplate restTemplate = new RestTemplate();
	//请求头
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
	//构建请求体
	HttpEntity<String> httpEntity = new HttpEntity<>("{\"name\":\"KevinBlandy\"}",httpHeaders);
	//执行REST请求,获取结果
	ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost/user", httpEntity, String.class);
	