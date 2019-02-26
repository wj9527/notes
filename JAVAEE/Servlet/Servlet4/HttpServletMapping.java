-------------------
HttpServletMapping |
-------------------
	# 对象获取
		HttpServletMapping httpServletMapping = request.getHttpServletMapping();
	# 接口方法
		public String getMatchValue();

		public String getPattern();
		
		public String getServletName();

		public MappingMatch getMappingMatch();
	
	# MappingMatch 枚举
		CONTEXT_ROOT
		DEFAULT
		EXACT
		EXTENSION
		PATH