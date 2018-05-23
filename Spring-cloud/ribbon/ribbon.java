----------------------------
ribbon						|
----------------------------
	# Spring Cloud Ribbon 是基于Netflix Ribbon实现的一套'客户端,负载均衡'工具
	# Ribbon是netflix发布的开源项目,主要提供客户端的软件负载均衡算法
	# LB(Load Balance)在微服务或者分布式集群中经常用的一种应用
	# 常见的负载均衡Nginx,LVS,硬件F5
	
	# 负载均衡的类型
		* 硬件LB
			* F5之类的,买不起
		* 进程内LB
			* 把LB逻辑集成到消费方,消费方从注册中心获取服务地址,再从地址中选择一个进行调用

----------------------------
整合						|
----------------------------
	# 坐标
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-ribbon</artifactId>
			<version>1.4.4.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
			<version>1.4.4.RELEASE</version>
		</dependency>
	
	# 开启负载均衡
		@Configuration
		public class RestTemplateConfig{

			@Bean
			@LoadBalance
			public RestTemplate restTemplate(){
				return new RestTemplate();
			}
		}

		* 给 RestTemplate 注册Ioc时,添加 @LoadBalance 注解
		

		

	