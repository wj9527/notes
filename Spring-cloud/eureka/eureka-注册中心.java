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