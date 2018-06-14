----------------------------
端点的状态监控				|
----------------------------
	# 依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
	
	# 常用端点
		/info 　　　　　　　应用基本信息
		/health 　　　　　　健康度信息
		/metrics 　　　　　	运行指标
		/env 　　　　　　　	环境变量信息
		/loggers 　　　　　日志相关
		/dump 　　　　　　	线程相关信息
		/trace 　　　　　　请求调用轨迹
	
	# 此时只能使用/health 和 /info端点,其他因为权限问题无法访问
	# 想访问指定端点的话可以在yml配置中添加相关的配置项
		/metrics
			endpoints.metrics.sensitive: false
		all
			endpoints.sensitive: false

	# 可修改状态信息的访问路径
		endpoints.info.path: /myinfo
		endpoints.health.path: /myhealth

----------------------------
spring-boot-admin-server	|
----------------------------
	# 一套监视系统
		* 用户手册:http://codecentric.github.io/spring-boot-admin/2.0.0/
	
	# 添加依赖
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server</artifactId>
			<version>1.5.7</version>
		</dependency>

		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server-ui</artifactId>
			<version>1.5.7</version>
		</dependency>
	
	# 注解驱动
		@EnableAdminServer
		@SpringBootApplication
		public class SpringbtAdminServerApplication {
			public static void main(String[] args) {
				SpringApplication.run(SpringbtAdminServerApplication.class, args);
			}
		}
	
	# 客户端添加依赖
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-starter-client</artifactId>
			<version>1.5.7</version>
		</dependency>
	
	# 客户端配置信息
		spring:
		  boot:
			admin:
			  url: http://localhost:8081
			  client:
				name: AdminTest
	
