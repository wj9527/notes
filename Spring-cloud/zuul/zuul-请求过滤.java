------------------------
zuul					|
------------------------
	# 请求过滤,实现接口: com.netflix.zuul.ZuulFilter
		@Bean		//注册ioc即生效
		public class DemoFilter extends ZuulFilter{
			@Override
			public boolean shouldFilter();
				* 判断当前过滤器是否被执行

			@Override
			public Object run();

			@Override
			public String filterType();
				* 定义过滤器的类型,它决定了过滤器在请求的哪个生命周期中执行,枚举字符串
					pre
						* 在请求处理之前执行
					

			@Override
			public int filterOrder();
				* 当存在多个过滤器的时候,该值定义了过滤器的执行顺序
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