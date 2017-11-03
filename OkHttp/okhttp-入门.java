---------------------------
okhttp-入门					|
---------------------------
	# 可以代替 HttpClient 工具包
	# 项目地址
		http://square.github.io/okhttp/
	# maven仓库
		<dependency>
			<groupId>com.squareup.okhttp3</groupId>
			<artifactId>okhttp</artifactId>
			<version>3.7.0</version>
		</dependency>

---------------------------
okhttp-Request				|
---------------------------
	Request.Builder builder = new Request.Builder();
	# URL相关
		url(URL url);
		url(String url);

	# 请求方法相关
		delete(RequestBody body);
		get();
		post(RequestBody body);

	# 请求头相关
		addHeader(String name, String value);
		removeHeader(String name);
		headers(Headers headers);
	
	# 创建 Request 对象
		build();

---------------------------
okhttp-RequestBody			|
---------------------------
	

---------------------------
okhttp-Response				|
---------------------------
	# 获取响应体
		ResponseBody body();
	
	# 判断相关
		boolean isRedirect();
			* 是否是重定向
		boolean isSuccessful();
			* 是否请求OK

	# 响应头相关
		String header(String headerName);
			* 获取指定名称的响应头

		String header(String name, String defaultValue);
			* 获取指定名称的响应头
			* 如果不存在,则返回默认值

		Headers headers();
			* 返回所有响应头

		List<String> headers(String name);
			* 获取指定响应头的所有值


---------------------------
okhttp-ResponseBody			|
---------------------------
	byte[] bytes();
	InputStream byteStream();
	Reader charStream();
	long contentLength();
	MediaType contentType();
	String string();

