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

		eureka.client.service-url.defaultZone=http://localhost:10086/eureka/
			# 注册中心的地址

		eureka.instance.prefer-ip-address=true
			# 在eurake管理控制台中,该服务连接的地址以ip的形式出现,默认为主机名

		eureka.instance.instance-id=${spring.cloud.client.ipAddress}:${server.port}
			# 在管控台中,实例连接的名称
		
------------------------
服务提供者-info信息		|
------------------------
	# 添加依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
	
	# pom.xml 配置
		* 可以直接配置在prarent/pom.xml 中,所有子级模块都能使用
		<build>
			<!-- 允许src/main/resources资源访问 -->
			<resources>
				<resource>
					<directory>src/main/resources</directory>
					<filtering>true</filtering>
				</resource>
			</resources>
			<plugins>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-resources-plugin</artifactId>
					<configuration>
						<delimiters>
							<!-- 变量的取值边界符 -->
							<delimit>$</delimit>
						</delimiters>
					</configuration>
				</plugin>
			</plugins>
		</build>
	
	# yml
		info: 
		 app.name: atguigu-microservicecloud					//应用的名称
		 company.name: www.atguigu.com							//公司名称
		 build.artifactId: $project.artifactId$					//artifactId,通过 $xx$ 来读取
		 build.version: $project.version$						//version,通过 $xx$ 来读取
		
	# 在管理控制台,就可以以json的形式来通过 ..../info 来获取到以上配置的info信息

