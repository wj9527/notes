-----------------------
RestTemplate		   |
-----------------------
	# http客户端
	# 基础自: InterceptingHttpAccessor 实现: RestOperations
	# 核心的组件
		private final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			* 消息转换器
			
		private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();
			* 异常处理器

		private UriTemplateHandler uriTemplateHandler;
		private final ResponseExtractor<HttpHeaders> headersExtractor = new HeadersExtractor();
		private final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
			* 请求拦截器

		private ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			* http请求工厂

	# 构造函数
		public RestTemplate(ClientHttpRequestFactory requestFactory)
		public RestTemplate()
		public RestTemplate(List<HttpMessageConverter<?>> messageConverters)


