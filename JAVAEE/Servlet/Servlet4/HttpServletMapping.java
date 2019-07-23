-------------------
HttpServletMapping |
-------------------
	# 动态的获取路由
		* 通俗理解，就是为了获取通配符映射的URI
		* restFull

	# 对象获取
		HttpServletMapping httpServletMapping = request.getHttpServletMapping();

	# 接口方法
		// 返回匹配到的value，也就是URI的映射值
		public String getMatchValue();
		
		// 返回处理请求的Servlet的pattern
		public String getPattern();
		
		// 返回Servlet的名称
		public String getServletName();

		// 返回匹配模式
		public MappingMatch getMappingMatch();
	
	# MappingMatch 枚举
		CONTEXT_ROOT
		DEFAULT

		EXACT
			* 精准匹配

		EXTENSION
			* 扩展名匹配
				xx.do
		PATH
			* 路径匹配
				/*
