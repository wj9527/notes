------------------------
swagger
------------------------
	# 官网
		https://swagger.io/
	
	
-----------------------------
swagger - springboot 整合
-----------------------------
	# springfox
		http://springfox.github.io/springfox/
		http://springfox.github.io/springfox/docs/current/

	
	# Maven
		<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
	
	# 启用 swagger
		@EnableSwagger2

	
	# 访问
		http://localhost/swagger-ui.html
		http://localhost/v2/api-docs