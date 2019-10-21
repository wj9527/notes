---------------------------
health					   |
---------------------------
	# app的运行状态节点
	
	# 相关的类
		HealthAggregator
			|-OrderedHealthAggregator
		HealthContributorRegistry
				|-DefaultHealthContributorRegistry
		HealthContributor
		Status
	
	
	# 预定义的 HealthIndicator 
		CassandraHealthIndicator
		CouchbaseHealthIndicator
		DiskSpaceHealthIndicator
		ElasticSearchRestHealthContributorAutoConfiguration
		HazelcastHealthIndicator
		InfluxDbHealthIndicator
		InfluxDbHealthIndicator
		JmsHealthIndicator
		MailHealthIndicator
		MongoHealthIndicator
		PingHealthIndicator
		RabbitHealthIndicator
		RedisHealthIndicator
		SolrHealthIndicator
		...
	
	# 自定义 HealthIndicator
		

---------------------------
配置项					   |
---------------------------
# 一般配置
management.health.defaults.enabled=false
	* 是否启用默认的健康健康项

management.endpoint.health.show-details=
	* 是否显示app状态的详情

management.endpoint.health.show-components=
	* 枚举值: 
		never			不会显示
		when-authorized	显示给授权用户
		always			显示给所有用户
management.endpoint.health.roles=
management.endpoint.health.<name>.order=fatal,down,out-of-service,unknown,up
	* 配置指定节点状态的严重性顺序

management.endpoint.health.<name>.http-mapping.<status>=503
	* 配置指定节点, 在指定状态下的http状态码

# 集群配置
management.endpoint.health.group.custom.include=db
management.endpoint.health.group.custom.show-details=when-authorized
management.endpoint.health.group.custom.roles=admin
management.endpoint.health.group.custom.status.order=fatal,up
management.endpoint.health.group.custom.status.http-mapping.fatal=500