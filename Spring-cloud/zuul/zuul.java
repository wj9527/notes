----------------------------
zuul						|
----------------------------
	# Zuul包含了对请求的:路由,过滤,代理  ...等核心的主要功能
	# 路由
		* 负责把外部请求转发到具体的微服务实例上
		* 是实现外部访问统一入口的基础

	# 过滤
		* 负责对请求的处理过程进行干预
		* 是实现请求校验,服务聚合等功能的基础
	
	# Zuul和Eureka整合,将Zuul自身注册为Eureka服务治理下的应用,同时从Eureka中获取其他微服务的消息
		* 以后访问微服务,都是通过Zuul跳转后获得
	
	# 'Zuul服务最终还是会注册进Eureka'


	# Maven
		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-zuul -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zuul</artifactId>
			<version>1.4.4.RELEASE</version>
		</dependency>

----------------------------
zuul-入门体验				|
----------------------------
	# 配置
		* 它也要当作一个服务,注册到Eureka中
		* 所以,它也需要服务提供者的那一套eureka配置
	
	# 驱动注解
		@EnableZuulProxy
	
	# 通过路由进行访问
		* 协议:网关主机:端口:服务名称/接口
		http://localhost:8081/user-service/user/1
		
		

