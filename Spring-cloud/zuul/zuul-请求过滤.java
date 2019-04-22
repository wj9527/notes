------------------------
zuul					|
------------------------
	# 请求过滤,实现接口: com.netflix.zuul.ZuulFilter
		@Bean		//注册ioc即生效
		public class DemoFilter extends ZuulFilter {
			@Override
			public boolean shouldFilter();
				* 判断当前过滤器是否被执行,如果返回 true,则执行:run() 方法

			@Override
			public Object run();
				* 执行校验的方法
				* 目前框架不会去处理返回值(忽略了返回值)

			@Override
			public String filterType();
				* 定义过滤器的类型,它决定了过滤器在请求的哪个生命周期中执行,枚举字符串
					pre(FilterConstants.PRE_TYPE)
						* 在请求被路由之前执行
					error(FilterConstants.ERROR_TYPE)
						* 在请求异常时候处理
					post(FilterConstants.POST_TYPE)
						* 在最后(route和error之后)调用
					route(FilterConstants.ROUTE_TYPE)
						* 在请求路由时调用
					

			@Override
			public int filterOrder();
				* 当存在多个过滤器的时候,该值定义了过滤器的执行顺序
				* 数值越小,优先级越高
		}
	
	# 校验小Demo
		@Override
		public Object run() {
			RequestContext requestContext = RequestContext.getCurrentContext();
			HttpServletRequest httpServletRequest = requestContext.getRequest();
			if(httpServletRequest.getHeader("auth") == null) {
				//不进行路由
				requestContext.setSendZuulResponse(false);
				//设置响应状态码为401
				requestContext.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			}
			return null;
		}


------------------------
异常处理				|
------------------------
	# 使用 SendErrorFilter 来处理
		SendErrorFilter
			* 它负责处理异常,但是它是否执行的条件是,上下文中存在异常,并且尚未转发到errorPath
				@Override
				public boolean shouldFilter() {
					RequestContext ctx = RequestContext.getCurrentContext();
					return ctx.getThrowable() != null && !ctx.getBoolean("sendErrorFilter.ran", false);
				}

		* 可以在执行过程中设置异常,来触发SendErrorFilter进行异常处理
			RequestContext requestContext = RequestContext.getCurrentContext();
			requestContext.setThrowable(new Exception());

		* 也可以添加如下的参数
			// 错误编码
			requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			// 异常对象
			requestContext.set("error.exception",new Exception());
			// 错误信息
			requestContext.set("error.message","错误信息");
	
	# 因为在请求的生命周期:pre,post,route 三个阶段中,只要有异常抛出都会进入到erro阶段的处理,所以可以自定义ErrorFilter
		
	
		

------------------------
FilterProcessor			|
------------------------
	FilterProcessor getInstance()
	void setProcessor(FilterProcessor processor)



------------------------
RequestContext			|
------------------------
	# 请求上下文
	# RequestContext
		HttpServletResponse getResponse();
        StringBuilder getFilterExecutionSummary();
        getOriginContentLength();
        getOriginResponseHeaders();
        getZuulResponseHeaders();
        getZuulEngineRan();
        getResponseGZipped();
        getResponseDataStream();
        getRequestQueryParams();
        getResponseStatusCode();
        getRouteHost();
        getResponseBody();

