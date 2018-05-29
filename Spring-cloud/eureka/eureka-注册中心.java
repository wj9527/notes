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
		
		eureka.instance.name=localhost
			# eureka服务端的实例名称
		eureka.client.fetch-registry=false
			# 当前eureka 仅仅作为注册中心(server),不会去检索服务
		eureka.client.register-with-eureka=false
			# 当前eureka仅仅充当注册中心,忽略自己作为服务提供者的注册行为
			

		eureka.client.service-url.defaultZone=http://localhost:${server.port}/eureka/
			# 服务提供者进行注册的地址,它是具备默认值的


------------------------
自我保护策略			|
------------------------
# 默认情况下,Eureka在一定时间内没有接收到某个微服务实例的心跳,Eureka会注销该实例(默认90秒)
	* 但是,当网络分区故障发生时,微服务与Eureka之间无法正常通信,以上行为可能变得非常危险了
	* 因为微服务本身是健康的,'不应该注销该微服务',Eureka通过'自我保护机制',来解决这个问题
	* 当Eurake节点在短时间内丢失了过多的客户端(服务提供者)时,那么这个节点就会进入自我保护模式
	* 进入该模式后,Eurake会保护服务注册表中的信息,不再进行删除表中的数据(也就是说不会注销任何服务)
	* 在网络故障恢复时,自动退出自我保护模式

# 在自我保护模式中,Eurake会保护注册表中的信息,不再删除任何服务实例
	* 当它收到的心跳数重新恢复到阈值以上时,Eurake Server节点就会自动退出自我保护模式
	* 它的设计哲学就是:'宁可保护错误的服务注册信息,也不盲目注销任何可能健康的服务实例'

# 自我保护模式是一种对应'网络异常的安全保保护措施'
	* 它的架构哲学是宁可同时保留所有微服务(监控的微服务,和不健康的服务都会保留)
	* 使用自我保护模式,可以让Eurake集群更加健壮文档

# 禁用自我保护模式
	eureka.server.enable-self-preservation=false
