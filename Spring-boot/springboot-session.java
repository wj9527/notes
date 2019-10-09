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

	
	# Cookie信息的自定义
		* 还是可以通过: server.servlet.session.cookie 来控制Cookie的名称属性等信息
		* 也可以通过代码, 自动配置: CookieSerializer

		@Bean
		public CookieSerializer cookieSerializer() {
			DefaultCookieSerializer serializer = new DefaultCookieSerializer();
			serializer.setCookieName("JSESSIONID"); 
			serializer.setCookiePath("/"); 
			serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); 
			return serializer;
		}
			

	# Session的相关spring事件
		SessionCreatedEvent				创建
		SessionDestroyedEvent
			|-SessionExpiredEvent		过期
			|-SessionDeletedEvent		删除
	

	# 自定义Session的解析方式
		* Session的解析依赖于一个接口: HttpSessionIdResolver
			CookieHttpSessionIdResolver	使用Cookie(默认)
			HeaderHttpSessionIdResolver	使用Header

		* 通过自定义配置类来实现自定义的解析
			@Bean
			public HttpSessionIdResolver httpSessionIdResolver() {
				return HeaderHttpSessionIdResolver.xAuthToken();  // 使用 X-Auth-Token 解析Cookie
			}
		
		* HeaderHttpSessionIdResolver 有N个工厂方法返回不同的实现

			HeaderHttpSessionIdResolver xAuthToken()
				* 使用: X-Auth-Token 头作为session的id

			HeaderHttpSessionIdResolver authenticationInfo()
				* 使用: Authentication-Info 头作为session的id

		* 也可以自定义请求头, 通过构造方法创建
			HeaderHttpSessionIdResolver(String headerName)

		* 它还提供了一些其他的方法
			List<String> resolveSessionIds(HttpServletRequest request)
				* 根据请求读取到会话id
			void setSessionId(HttpServletRequest request, HttpServletResponse response,String sessionId)
				* 设置会话id, 本质上就是给客户端响应Token头
			void expireSession(HttpServletRequest request, HttpServletResponse response);
				* 过期会话id, 本质上就是给客户端响应空的Token头


			
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




-------------------------------------
SessionRepository<S extends Session> |
-------------------------------------
	# Session的管理接口
		S createSession();
			* 创建一个
		void save(S session);
			* 存储/创建
		S findById(String id);
			* 根据id检索
		void deleteById(String id);
			* 根据id删除
	
	# 子类实现
		SessionRepository
			|-MapSessionRepository
			|-FindByIndexNameSessionRepository
				|-RedisOperationsSessionRepository
				|-RedissonSessionRepository

--------------------------------
FindByIndexNameSessionRepository|
--------------------------------
	# 通俗的说, 就是可以根据用户获取到它的Session
	# 用户登录后要设置一个数据到Session来存储关联信息
		String username = "username";
		this.session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);

		* 一般可以用ID之类的数据关联, 不一定是name
	
	# 根据用户信息检索用户
		@Autowired
		FindByIndexNameSessionRepository<? extends Session> sessions;

		@RequestMapping("/")
		public String index(Principal principal, Model model) {
			Collection<? extends Session> usersSessions = this.sessions.findByPrincipalName(principal.getName()).values();
			model.addAttribute("sessions", usersSessions);
			return "index";
		}
	
	# FindByIndexNameSessionRepository 的方法和属性

		String PRINCIPAL_NAME_INDEX_NAME = FindByIndexNameSessionRepository.class.getName().concat(".PRINCIPAL_NAME_INDEX_NAME");

		Map<String, S> findByIndexNameAndIndexValue(String indexName, String indexValue);
			* 根据指定的key/value检索session

		default Map<String, S> findByPrincipalName(String principalName) {
			return findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, principalName);
		}
			
	
	# 原理
		* FindByIndexNameSessionRepository 本质上是提供了一个api, 可有根据key和value检索到session
			findByIndexNameAndIndexValue(key, value)
		
		* 可以借助于这个功能, 可以实现用户和会话的绑定

	

	