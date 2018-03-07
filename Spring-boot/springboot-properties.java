-------------------------------
Spring boot-配置项				|
-------------------------------
	// Server相关
		server.port=80
			# WEB监听端口

		server.context-path=/
			# WEB访问路径
	
	//编码处理
		server.tomcat.uri-encoding=UTF-8
		spring.http.encoding.charset=UTF-8
		spring.http.encoding.enabled=true

	//日期格式处理
		spring.mvc.date-format=yyyy-MM-dd HH:mm:ss

	//日志
		logging.config=classpath:community-logback.xml
			# logback配置文件地址
	
	//静态文件映射
		spring.mvc.static-path-pattern=/static/**													*/
			# 用于指定静态文件的目录(在classpath目录下-src/main/resources),允许外界直接访问
		
		spring.resources.static-locations[0]=
			# 也是静态资源的映射处理,是一个数组,支持多个
			# 支持 classpath:/ ,支持 file:/

	//MyBatis
		mybatis.mapper-locations[0]=classpath*:mapper/**/Mapper.xml
			# mybatis mapper文件扫描地址

		mybatis.config-location=classpath:mybatis/mybatis-config.xml
			# mybatis配置文件地址

	//导入外部配置文件
		spring.profiles.include[0]=datasource
		spring.profiles.include[1]=redis
