eureka.client.fetch-registry=true
eureka.client.register-with-eureka=true
eureka.client.service-url.defaultZone=http://localhost:8080/eruake
	# 注册中心地址

eureka.instance.prefer-ip-address=true
	# eureka管控台中服务提供者的连接地址使用IP地址,默认为主机名

eureka.instance.instance-id=${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${server.port}}
	# eureka管控台中的服务的名称,默认:主机名:应用名:应用端口
	# 可以修改为:${spring.cloud.client.ipAddress}:${server.port}

eureka.instance.status-page-url=${management.context-path}/info
	# 实例状态页面地址

eureka.instance.health-check-url=${management.context-path}/health
	# 运行状况指示器地址
