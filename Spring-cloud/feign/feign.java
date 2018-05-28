------------------------
feign					|
------------------------
	# 是是一个声明式的WebService客户端
		* 使用Feign能让编写WebService客户端更加的简单
		* 它的使用方法是定义一个借口,然后在接口上添加注解,同时也支持JAX-RS标准的注解
		* Feign也支持可热拔插式的编码和解码器

	# SpringCloud对Feign进行了封装,使其支持了SpringMVC标准注解和HttpMessageConverters

	# Feign可以和Eureka,Ribbon组合使用,以支持负载均衡
		* Feign默认集成了Ribbon,利用Ribbon维护了微服务提供者的列表信息,并且通过轮询实现了客户端的负载均衡
		* 与Ribbon不同,feign只需要定义服务绑定接口且以声明式的方法,简单而又优雅的实现了服务的调用

	# 坐标
		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-feign -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-feign</artifactId>
			<version>1.4.4.RELEASE</version>
		</dependency>

------------------------
入门体验				|
------------------------
	# 注解开启
		@EnableFeignClients
		@SpringBootApplicatoon
		@EnableEurekaClient

			* 通过 @EnableFeignClients 来启动Feign驱动

	# 定义接口
		@FeignClient("USER-SERVICE")
		public interface UserService {
			
			@GetMapping("/user/{userId}")
			User getById (@PathVariable("userId")Integer id);
		}
		
		* @FeignClient 通过该注解,指定微服务的名称
		* 使用路由注解(@GetMapping)来指定调用路径,通过 @PathVariable 来绑定参数
		* 跟mybatis的mapper一样,动态生成实现载入IOC中

	# 使用 FeignClient 接口
		
		@Autowired
		private UserService userService;
		
		@GetMapping("/user")
		public User getUser(@RequestParam("id")int id){
			//以接口的形式调用微服务
			return this.userService.getById(id);
		}
	


