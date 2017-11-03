------------------------
Shiro-整合Spring		|
------------------------
	

------------------------
Shiro-Shiro Filter		|
------------------------
	# 在web.xml中配置一个filter
		<!-- shiro filter -->
		<filter>
			<filter-name>shiro</filter-name>
			<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
		</filter>
		<filter-mapping>
			<filter-name>shiro</filter-name>
			<url-pattern>/*</url-pattern>								
			<dispatcher>REQUEST</dispatcher>
			<dispatcher>FORWARD</dispatcher>
			<dispatcher>INCLUDE</dispatcher>
			<dispatcher>ERROR</dispatcher>
		</filter-mapping>																	*/
	
	# 这个拦截器会代替Shiro的filter
	
