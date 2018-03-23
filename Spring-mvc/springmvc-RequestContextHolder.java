-----------------------------
RequestContextHolder		 |
-----------------------------
	# 当前request的一个线程安全容器
	# 获取当前的请求数据信息
		 RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes()

		 * RequestAttributes 是一个接口

	# 获取当前请求的HttpServletRequest
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		* ServletRequestAttributes 是 RequestAttributes 子类
