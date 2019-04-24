--------------------------------
ConfigServer					|
--------------------------------
	# Maven
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-config-server</artifactId>
			</dependency>
	
	# 默认使用Git仓库作为配置仓库
		* 配置文件必须已经被版本控制器管理
		* spring.config.server.git.uri 配合的根路径,必须要有 .git 文件夹(必须配置到根目录)
		* 并且配置文件需要提交到版本控制
	
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
				  # git配置仓库的位置(不能以为 / 结尾),根目录下必须要有: .git 目录(说白了,必须是GIT的根目录)
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
	
	
	
	# 可以使用本地的 git 仓库作为配置仓库
		spring.config.server.git.uri=file:D:\\config-rep\\springcloud-config

		* 使用:file: 开头表示使用本地的配置仓库
	
	# 使用URI占位符来区分不同的应用
		* application,profile,label 不仅仅可以标识配置文件规则

		* 还可以用于ConfigServer对于Git仓库的Uri地址
			spring.config.server.git.uri=https://github.com/KevinBlandy/{application}-config.git
			
			application
				* 表示应用名,当客户端发起请求的时候,Server会根据客户端的 spring.application.name 信息来填充URI
				* 就可以根据不同的服务,来动态的加载不同URI下的资源
			
			label
				* 这个参数比较特别,如果Git分支名称包含了 '/' ,那么在label参数在http的uri中应该使用 '_' 来代替,避免改变了URI的含义

			* 目前测试,好像占位符{application}不支持配置在 uri 属性中,如果使用 {application},在ConfigServer启动的时候会被替换为 : app,从而导致系统异常,提示不存在的目录
				配置 -> uri: 'https://github.com/KevinBlandy/{application}-config-rep.git'
				异常 -> Cannot clone or checkout repository: https://github.com/KevinBlandy/app-config-rep.git

			* 其实也可以解决,亲测,在ConfigServer启动的时候,先在根路径创建目录: {application},application替换为 app,并且初始化为git目录,并且有commit文件
				配置 -> uri: 'file:D:\\config-rep\\{application}-config'
				新建 -> D:\\config-rep\\app-config
		
		* 占位符还可以使用在搜索路径上,以此来区分同一个仓库下的不同文件夹
			spring
			  cloud:
			    config:
			      server:
				    # Git的根目录
				    uri: 'file:D:\\config-rep'
				    search-paths:
					  # 如果是yml的话,要使用双引号
					  - '{application}-config'

			* 根据不用应用名,去不同的子目录下搜索
			* 这个靠谱,经过试验没啥问题

		
