----------------------------
Eureka的配置项				|
----------------------------

	eureka.instance.name
		# 节点的名称
	eureka.instance.hostname
		# 节点的主机名称
	eureka.instance.instance-id
		# 节点的id
	eureka.instance.lease-renewal-interval-in-seconds
		# 服务续约任务的调用时间间隔,默认30s
	eureka.instance.lease-expiration-duration-in-seconds
		# 服务时效的时间,默认90s,就是说多少秒没有收到心跳算是服务失效


	eureka.client.fetch-registry
		# 是否从注册中心发现服务
	eureka.client.register-with-eureka
		# 是否注册服务到注册中心
	eureka.client.service-url.defaultZone
		# 注册中心的地址

	
	eureka.server.enable-self-preservation
		# 是否开启自我保护模式,默认 true

