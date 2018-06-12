----------------------------
访问映射规则				|
----------------------------
	# 单实例配置
		zuul:
		 routes:
		  user-api:
		   path:/api/**					**/
		   url:http://springboot.io/
		
		*  /api/user/1 会被转发到 ==> http://springboot.io/user1
	
	#  多实例配置
		zuul:
		 routes:
		  user-api:
		   serviceId: USER
		   path:/api/**					**/
		
		ribbon.eureka.enabled=false
		USER.ribbon.listOfServers=http://localhost:8080/,http://localhost:8081/

		* 就是把对path的访问,转发到服务名为USER的服务列表
		* USER是个自定义的服务名称


	# 服务路由配置
		zuul:
		 ignored-service: USER
		 routes:
		  user:
		   serviceId: USER
		   path: /myuser/**								**/

		
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
	
----------------------------
自定义路由映射规则			|
----------------------------
	# 实例化:PatternServiceRouteMapper
		
		@Bean
		public PatternServiceRouteMapper patternServiceRouteMapper(){
			return new PatternServiceRouteMapper("<name>","${name}");
		}

		* 可以使用正则来匹配出转发的路径
	