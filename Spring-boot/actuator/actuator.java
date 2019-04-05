----------------------------
actuator					|
----------------------------
	# 参考文档
		https://blog.csdn.net/alinyua/article/details/80009435
		https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready
	
	# 依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
	
	

	# 相关的配置类
		WebEndpointProperties
	
	# 可以访问的的端点
		ID				描述																															默认启用
		auditevents		显示当前应用程序的审计事件信息																									Yes
		beans			显示一个应用中所有Spring Beans的完整列表																						Yes
		conditions		显示配置类和自动配置类(configuration and auto-configuration classes)的状态及它们被应用或未被应用的原因							Yes
		configprops		显示一个所有@ConfigurationProperties的集合列表																					Yes
		env				显示来自Spring的 ConfigurableEnvironment的属性																					Yes
		flyway			显示数据库迁移路径,如果有的话																									Yes
		health			显示应用的健康信息(当使用一个未认证连接访问时显示一个简单的'status',使用认证连接访问则显示全部信息详情)							Yes
		info			显示任意的应用信息																												Yes
		liquibase		展示任何Liquibase数据库迁移路径，如果有的话																						Yes
		metrics			展示当前应用的metrics信息																										Yes
		mappings		显示一个所有@RequestMapping路径的集合列表																						Yes
		scheduledtasks	显示应用程序中的计划任务																										Yes
		sessions		允许从Spring会话支持的会话存储中检索和删除(retrieval and deletion)用户会话。使用Spring Session对反应性Web应用程序的支持时不可用	Yes
		shutdown		允许应用以优雅的方式关闭(默认情况下不启用)																						No
		threaddump		执行一个线程dump																												Yes

		heapdump		返回一个GZip压缩的hprof堆dump文件																								Yes
		jolokia			通过HTTP暴露JMX beans(当Jolokia在类路径上时，WebFlux不可用)																		Yes
		logfile			返回日志文件内容(如果设置了logging.file或logging.path属性的话),支持使用HTTP Range头接收日志文件内容的部分信息					Yes
		prometheus		以可以被Prometheus服务器抓取的格式显示metrics信息																				Yes



	
	

