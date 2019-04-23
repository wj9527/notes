--------------------------------
ConfigClient					|
--------------------------------
	# Maven
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-client</artifactId>
		</dependency>
	
	# 配置
		* 注意,要配置在 : bootstrap.yml 中

		spring:
		  application:
		    # 就是文件的前缀名
			name: springcloud
		  cloud:
			config:
			  # 配置文件服务器
			  uri: http://localhost:8015/
			  # 激活的文件,就是文件的后缀
			  profile: dev
			  # 分支
			  label: master
			
		* 应用启动的时候会根据 application.name 和 config.profile 还有 config.label 去 config.uri 上去加载配置文件信息
			{label} ==> {application}-{profile}.yml

	# 在程序里面使用
		// 直接使用 @Value 注解
		@Value("${config.name}")
		private String configName;
		
		// 使用 environment
		@Autowired
		private Environment environment;
		environment.getProperty("config.name")