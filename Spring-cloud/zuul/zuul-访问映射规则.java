----------------------------
访问映射规则				|
----------------------------
	# 映射规则配置
		zuul:
		 ignored-service: USER
		 routes:
		  user.serviceId: USER
		  user.path: /myuser/**								**/
		
		* ignored-service 属性非必须,它的存在就是废除指定服务的原有访问地址
			* 也就是说,必须使用routes定义的新地址
			* 如果该值为: * (星号),则表示废除所有的微服务原始访问地址

		* routes 像是一个Map
		* 里面定义了:
			路由名称.serviceId: 服务名称
			路由名称.path: 服务新的访问地址
		
		* http://网关ip:网关端口/路由名称/接口地址
	

	# 设置统一公共前缀
		zuul:
		 predix: /api
	
		* 所有的微服务前面都要加: /api
	
