----------------------------------
freemarker						  |
----------------------------------
	# 官方依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-freemarker</artifactId>
		</dependency> 
	
	# 配置项
		spring.freemarker.allow-request-override=false # Set whether HttpServletRequest attributes are allowed to override (hide) controller generated model attributes of the same name.
		spring.freemarker.allow-session-override=false # Set whether HttpSession attributes are allowed to override (hide) controller generated model attributes of the same name.
		spring.freemarker.cache=false # Enable template caching.
		spring.freemarker.charset=UTF-8 # Template encoding.
		spring.freemarker.check-template-location=true # Check that the templates location exists.
		spring.freemarker.content-type=text/html # Content-Type value.
		spring.freemarker.enabled=true # Enable MVC view resolution for this technology.
		spring.freemarker.expose-request-attributes=false # Set whether all request attributes should be added to the model prior to merging with the template.
		spring.freemarker.expose-session-attributes=false # Set whether all HttpSession attributes should be added to the model prior to merging with the template.
		spring.freemarker.expose-spring-macro-helpers=true # Set whether to expose a RequestContext for use by Spring's macro library, under the name "springMacroRequestContext".
		spring.freemarker.prefer-file-system-access=true # Prefer file system access for template loading. File system access enables hot detection of template changes.
		spring.freemarker.prefix= # Prefix that gets prepended to view names when building a URL.
		spring.freemarker.request-context-attribute= # Name of the RequestContext attribute for all views.
		spring.freemarker.settings.*= # Well-known FreeMarker keys which will be passed to FreeMarker's Configuration.
		spring.freemarker.suffix= # Suffix that gets appended to view names when building a URL.
		spring.freemarker.template-loader-path=classpath:/templates/ # Comma-separated list of template paths.
		spring.freemarker.view-names= # White list of view names that can be resolved.
		spring.freemarker.template-encoding=UTF-8
	
	# 最佳体验
spring:
  freemarker:
    request-context-attribute: req  #req访问request
    suffix: .html  #后缀名
    content-type: text/html
    enabled: true
    cache: false #缓存配置
    template-loader-path: classpath*:/templates/ #模板加载路径 按需配置
    charset: UTF-8 #编码格式
    settings:
      number_format: '0.##'   #数字格式化，无小数点

