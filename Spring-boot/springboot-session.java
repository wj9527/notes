----------------------------
session						|
----------------------------
	# 参考
		https://docs.spring.io/spring-session/docs/current/reference/html5/
	
	# 依赖
		<dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session-data-redis</artifactId>
		</dependency>
	
	# 注解驱动 @EnableRedisHttpSession (不是必须的)
		int maxInactiveIntervalInSeconds() default MapSession.DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS;
			* session的最大存活时间, 默认是秒

		String redisNamespace() default RedisOperationsSessionRepository.DEFAULT_NAMESPACE;
			* redis的存储空间key名称

		RedisFlushMode redisFlushMode() default RedisFlushMode.ON_SAVE;
			* 刷新模式, 枚举
				ON_SAVE
					* 只有当 SessionRepository.save(Session)方法被调用时, 才会将session中的数据同步到redis中.
					* 在web 应用中, 当请求完成响应后, 才开始同步. 也就是说在执行response 之前session数据都是缓存在本地的.

				IMMEDIATE
					* 实时同步session 数据到redis. 
					* 当执行SessionRepository.createSession()时, 会将session数据同步到redis中; 
					* 当对session的attribute进行set/remove 等操作时, 也会同步session中的数据到redis中

		String cleanupCron() default RedisHttpSessionConfiguration.DEFAULT_CLEANUP_CRON;
			* 执行session清理的cron表达式
			* 默认: 0 * * * * * (一分钟执行一次)

	
	# 还是可以通过: server.servlet.session.cookie 来控制Cookie的名称属性等信息
		
----------------------------
配置						|
----------------------------
spring:
  session:
    timeout: 1800
		* 会话的超时时间, 单位是秒,默认: 1800
		* 如果不设置, 默认使用: server.servlet.session.timeout
    store-type: REDIS
		* 指定session的存储方式, 枚举
			REDIS
			MONGODB
			JDBC
			HAZELCAST
			NONE
    redis:
	  flush-mode: on-save
	  namespace: 


	
	

