eureka.server.enable-self-preservation=false
	# 设为false,关闭自我保护
	# 当Eureka Server节点在短时间内丢失过多客户端时(可能发生了网络分区故障),那么这个节点就会进入自我保护模式
	# 一旦进入该模式,Eureka Server就会保护服务注册表中的信息,不再删除服务注册表中的数据(也就是不会注销任何微服务)
	# 当网络故障恢复后,该Eureka Server节点会自动退出自我保护模式
eureka.server.eviction-interval-timer-in-ms=60000    
	# 清理间隔(单位毫秒,默认是60 * 1000)

eureka.client.fetch-registry=true
eureka.client.register-with-eureka=true
eureka.client.service-url.defaultZone=http://localhost:8080/eruake
	# 注册中心地址
eureka.client.healthcheck.enabled=true			
	# 开启健康检查(需要spring-boot-starter-actuator依赖)
	# 如果需要更细粒度健康检查,可实现com.netflix.appinfo.HealthCheckHandler接口

eureka.instance.prefer-ip-address=true
	# 如果该值为true,eureka管控台中服务提供者的连接地址使用IP地址,默认为主机名

eureka.instance.instance-id=${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${server.port}}
	# eureka管控台中的服务的名称,默认:主机名:应用名:应用端口
	# 可以修改为:${spring.cloud.client.ipAddress}:${server.port}

eureka.instance.status-page-url=${management.context-path}/info
	# 实例状态页面地址

eureka.instance.health-check-url=${management.context-path}/health
	# 运行状况指示器地址


eureka.instance.lease-renewal-interval-in-seconds		
	# 续约更新时间间隔(默认30秒)
eureka.instance.lease-expiration-duration-in-seconds 	
	# 续约到期时间(默认90秒)
	# 如果Eureka Server 在一定时间内没有接收到某个微服务实例的心跳,Eureka Server将会注销该实例(默认90秒)