----------------------------
注册中心					|
----------------------------
	# 各个注册中心节点的名称要修改
		* 尽量保证集群节点中所有成员的instance.name不一样
		* 命名方式可以带上端口等信息
		* 可以通过本地host文件来做映射

		eureka.instance.name=localhost10086.com
		eureka.instance.name=localhost10087.com
		eureka.instance.name=localhost10088.com

	# 允许注册与发现,默认值就是true,其实不用手动声明
		fetch-registry: true
		register-with-eureka: true
	
	# 注册地址的修改
		* 在 defaultZone 添加上当前节点以外的所有节点成员的注册地址

		eureka.client.service-url.defaultZone=http://localhost10086.com:10086/eureka,http://localhost10088.com:10088/eureka
			* 当前节点为:10087,所以只需要添加 86 和 88 节点

----------------------------
服务提供者					|
----------------------------
	# 注册中心地址修改
		* 服务提供者的注册地址修改为集群中的所有节点

		eureka.client.service-url.defaultZone=http://localhost10086.com:10086/eureka,http://localhost10087.com:10087/eureka,http://localhost10088.com:10088/eureka



----------------------------
服务消费者					|
----------------------------
	# 注册中心地址修改
		* 服务消费检索服务的地址修改为集群中的所有节点

		eureka.client.service-url.defaultZone=http://localhost10086.com:10086/eureka,http://localhost10087.com:10087/eureka,http://localhost10088.com:10088/eureka