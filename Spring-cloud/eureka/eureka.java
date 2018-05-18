------------------------
eureka					|
------------------------
	# 介绍
		* 由 Netflix 开发的一个服务发现框架
		* 本身是一个基于REST的服务
		* 主要用于定位运行在AWS(亚马逊云服务)域中的中间层服务,以达到负载均衡和中间层故障转移的目的
		* spring cloud 将它集成在子项目 spring-cloud-netflix 中,以实现spring cloud的服务发现功能

	# 优点
		* eureka 来自于生存环境
		* spring cloud 对 eureka 支持良好
		* 项目活跃,迭代频繁,目前最新版本:1.3.5
		
	
	# 原理
	

	# wiki
		https://github.com/Netflix/eureka/wiki
		

------------------------
注册中心				|
------------------------
	# maven依赖
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka-server</artifactId>
			<version>1.3.5.RELEASE</version>
		</dependency>
	
	# 开启 eureka server
		@SpringBootApplication
		@EnableEurekaServer			//通过 @EnableEurekaServer 注解开启 eureka 注册中心
		public class RegisterApplication {
			public static void main(String[] args)throws Exception{
				SpringApplication.run(RegisterApplication.class,args);
			}
		}


	# 配置项
		
		eureka.client.fetch-registry=false
			# 当前eureka 仅仅作为注册中心(server),不会去检索服务
		eureka.client.register-with-eureka=false
			# 当前eureka仅仅充当注册中心,忽略自己作为服务提供者的注册行为
			

		eureka.client.service-url.defaultZone=http://localhost:${server.port}/eureka
			# 服务提供者进行注册的地址,它是具备默认值的

------------------------
服务提供者				|
------------------------
	# maven依赖
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
			<version>1.3.5.RELEASE</version>
		</dependency>
	
	
	# 开启服务自动注册到注册中心
		@SpringBootApplication
		@EnableEurekaClient		//当前为eureka的客户端
		public class UserServiceApplication {
			
			public static void main(String[] args) {
				SpringApplication.run(UserServiceApplication.class, args);
			}
		}
		* @EnableEurekaClient 表示了当前微服务是通过 eureka 框架进行服务注册的,不能通过其他的
		* 可以使用:@EnableDiscoveryClient 注解,该注解是一个接口,可以适用于所有服务治理的框架

	# 配置项
		spring.application.name=example-user-service
			# 当前微服务的名称,会以大写的形式出现在 eureka 的控制台
		eureka.client.service-url.defaultZone=http://localhost:10086/eureka
			# 注册中心的地址
		eureka.instance.prefer-ip-address=true
			# 在eurake管理控制台中,该服务连接的地址以ip的形式出现,默认为主机名
		eureka.instance.instance-id=${spring.cloud.client.ipAddress}:${server.port}
			# 在管控台中,实例连接的名称
------------------------
服务消费者				|
------------------------
	# maven依赖
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
			<version>1.3.5.RELEASE</version>
		</dependency>
	
	# 配置项


------------------------
服务认证				|
------------------------
	# 保证只有可信任的服务提供者,才能注册到注册中心
	# 简单的认证配置
		security.basic.enabled=true
			# 开启安全配置
		security.user.name=root
			# 配置用户名
		security.user.password=root
			# 配置密码
		eureka.client.service-url.defaultZon=http://${security.user.name}:${security.user.password}@localhost:8761/eureka
			# 服务注册时候,使用配置的用户名和密码来进行注册
		
		* 需要添加依赖					
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-security</artifactId>
			</dependency>
		* 该配置成功后,不仅仅是服务实例需要通过账户名密码来进行连接
			eureka.client.service-url.defaultZon=http://root:root@localhost:8761/eureka
		* 登录eurake管理控制台也需要该账户名和密码来完成登录

	# 通过组件来完成服务的认证 TODO
		DiscoveryClientOptionArgs

------------------------
实例监控接口			|
------------------------
	# 该接口会返回JSON格式的实例状态信息
	# 需要添加依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		
		* 不多解释,spring-boot的一个适用于生产环境的监控组件

	# 配置项
		eureka.instance.status-page-url=${management.context-path}/info
			# 实例状态页面地址
		eureka.instance.health-check-url=${management.context-path}/health
			# 运行状况指示器地址

------------------------
HTTPS					|
------------------------
	TODO

------------------------
健康检查				|
------------------------
	# Spring Boot Actuator提供了/health端点,该端点可展示应用程序的健康信息
	# 只有将服务实例的健康状态传播到Eureka Server就可以了,实现这点很简单
	# 服务实例配置项
		eureka.client.healthcheck.enabled=true			
			# 开启健康检查(需要spring-boot-starter-actuator依赖)


------------------------
注册中心集群			|
------------------------

------------------------
注解					|
------------------------
	@EnableEurekaServer
		# 开启 eureka 注册中心服务

	@EnableEurekaClient
		# 开启 eureka 微服务,并且自动注册到注册中心


	
	