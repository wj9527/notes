--------------------------------
ConfigServer					|
--------------------------------
	# Maven
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-config-server</artifactId>
			</dependency>
	
	# 注解驱动
		@EnableConfigServer
	
	# 基本的配置
		server:
		  port: 8015

		spring:
		  application:
			name: config-server
		  cloud:
			config:
			  server:
				git:
				  # git配置仓库的位置(不能以为 / 结尾)
				  uri: https://github.com/KevinBlandy/temp-config.git
				  # 配置仓库下的相对搜索路径,可以有多个
				  search-paths:
					- config
				  # 访问仓库的账户名密码
				  username: KevinBlandy
				  password: F8575532
	
	# 服务启动OK后,可以通过浏览器访问
		http://localhost:8015/config/springcloud.yml

		* 可以访问的路径
			/{application}/{profile}/{label}

			/{application}-{profile}.yml
			/{label}/{application}-{profile}.yml

			/{application}-{profile}.properties
			/{label}/{application}-{profile}.properties
			
			application
				* 要以客户端的 spring.application.name 来定义
			profile
				* 环境
			label
				* 可以指定分支,默认为MASTER
		
		* 注意,要带上 search-paths 路径
	
	# ConifgServer还会在本地缓存配置文件
		* 本地缓存的目录
			C:\Users\KevinBlandy\AppData\Local\Temp\config-repo-[hash]
		
		* 防止Git服务器宕机而无法加载配置
	
