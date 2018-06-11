------------------------
feign					|
------------------------
	# 是是一个声明式的WebService客户端
		* 使用Feign能让编写WebService客户端更加的简单
		* 它的使用方法是定义一个借口,然后在接口上添加注解,同时也支持JAX-RS标准的注解
		* Feign也支持可热拔插式的编码和解码器

	# SpringCloud对Feign进行了封装,使其支持了SpringMVC标准注解和HttpMessageConverters

	# Feign可以和Eureka,Ribbon组合使用,以支持负载均衡
		* Feign默认集成了Ribbon,Hystrix,利用Ribbon维护了微服务提供者的列表信息,并且通过轮询实现了客户端的负载均衡
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
	

	# 配置
		feign.hystrix.enabled: true
			* 开启熔断器,不开启好像 fegin不起作用

	# 使用 FeignClient 接口
		
		@Autowired
		private UserService userService;
		
		@GetMapping("/user")
		public User getUser(@RequestParam("id")int id){
			//以接口的形式调用微服务
			return this.userService.getById(id);
		}
	


------------------------
数据压缩				|
------------------------
	# fegin支持对请求和响应进行zip压缩,减少带宽的传输
	# 开启配置
		feign.compression.request.enabled=true
		feign.compression.response.enabled=true
	
	# 更细致的配置
		fegin.compression.request.mime-types=text/xml.application/xml,application/json
			* 哪些数据需要压缩
			*  默认值:text/xml.application/xml,application/json

		fegin.compression.request.min-request-size=2048
			* 数据量达到多大时进行压缩
			* 默认值:2048

------------------------
日志配置				|
------------------------
	# feign在为 @FeignClient 构建客户端的时候,会为每一个客户端都创建一个: feign.Logger 实例对象
		* 可以利用该日志对象的DEBUG模式来分析Feign的请求细节
	
	# 开启指定客户端的日志
		1,配置客户端的日志
			logging.level.<FeignClient>=DBUG

			* FeignClient就是客户端的接口全路径
		
		2,创建全局 Logger.Level
			@Bean
			public Logger.Level feignnLoggerLevel(){
				return Logger.Level.FULL;
			}

			* feign默认的 Logger.Level 对象定义为NONE,不会记录任何Feign调用过程中的信息
	
		3,针对于指定服务指定 Logger.Level
			@Configuration 
			public class FullLogConfiguration{
				@Bean
				public Logger.Level feignnLoggerLevel(){
					return Logger.Level.FULL;
				}
			}
			
			@FeignClient(value = "USER-SERVICE",configuration = {FullLogConfiguration.class})
		
	# Feign的Logger主要有 四个
		NONE
			* 不记录任何信息
		BASIC
			* 仅仅记录请求方式,URL,响应状态码和执行时间
		HEADERS
			* 除了BASIC以外还会记录请求头和响应头信息
		FULL
			* 记录所偶


