@EnableDiscoveryClient
	# 开启当前微服务的自动注册,该注解是一个抽象接口,可以兼容所有的微服务治理框架
@EnableEurekaServer
	# 开启 eureka 注册中心服务

@EnableEurekaClient
	# 开启 eureka 微服务,并且自动注册到注册中心

@EnableZipkinServer
@EnableHystrix
@EnableHystrixDashboard