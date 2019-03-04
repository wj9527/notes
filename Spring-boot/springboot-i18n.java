---------------------
i18n 国际化			 |
---------------------
	# 类库
		LocaleResolver
			AbstractLocaleResolver
				AbstractLocaleContextResolver
					FixedLocaleResolver
					SessionLocaleResolver
			LocaleContextResolver
				AbstractLocaleContextResolver
					FixedLocaleResolver(固定的)
					SessionLocaleResolver(根据session)
				CookieLocaleResolver(根据cookie)
			AcceptHeaderLocaleResolver(根据Header)


			LocaleChangeInterceptor(国际化的拦截器)


---------------------
CookieLocaleResolver |
---------------------
	
---------------------
LocaleContextHolder	 |
---------------------
	# 当前语言环境的容器 ThreadLocal
	# 提供了一些静态方法
		Locale getLocale()
			* 获取当前环境的 Locale 对象
		
	