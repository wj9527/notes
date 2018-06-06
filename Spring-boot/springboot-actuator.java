----------------------------
端点的状态监控				|
----------------------------
	# 依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
	
	# 修改状态信息的访问路径
		endpoints.info.path: /myinfo
		endpoints.health.path: /myhealth
