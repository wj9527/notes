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
		eureka.client.register-with-eureka=false
			# 以上两项,表示当前eureka仅仅充当注册中心,忽略自己作为服务提供者的注册行为
			# eureka 作为注册中心(server),本身内部其实还会作为服务提供者进行注册

		eureka.client.service-url.defaultZone=http://localhost:${server.port}/eureka
			# 服务提供者进行注册的地址

------------------------
服务提供者				|
------------------------
	# maven依赖
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
			<version>1.3.5.RELEASE</version>
		</dependency>
	

	# 配置项
		

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
注册中心集群			|
------------------------

------------------------
监控中心				|
------------------------

------------------------
配置项大全				|
------------------------
	
	