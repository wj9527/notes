
--------------------------------------
springboot配置	https				  |
--------------------------------------
	# 使用jdk系统工具生成证书
		* 使用的是$JAVA_HOME/bin/keytool 工具(JAVA里面自带的工具)

		* keytool -genkey -alias tomcat -validity 36500 -keystore D:\home\tomcat.keystore -keyalg RSA

			* -genkey		:表示产生密钥对
			* -alias		:表示起个别名
			* -keyalg		:指定密钥算法
			* -validity		:密钥有效时间,默认为90天,36500.表示100年
			* -keystore		:指定密钥保存的路径

		
	
		* 输入 keystore 密码
			产生的证书,系统会使用一个密钥库来保存,这里就是设置密钥库的密码
		
		* 您的名字与姓氏是什么?
			这一步很重要,表示为哪个网站生成数字证书,填写域名
		
		* 您的组织单位名称是什么？
			* 无视
		
		* 您的组织名称是什么？
			* 无视
		
		* 您所在的城市或者区域名称是什么?
			* 无视
		
		* 您所在的洲,或省份是什么?
			* 无视
		
		* 该单位的两字母国家代码是什么?
			* 无视
		
		* CN=localhost,OU=Unknow,O=Unknow,L=Unknow,ST=Unknow,C=Unknow 正确吗?
			* 确定输入: y
		
		* 输入 <tomcat> 的主密码(如果和 keystore 密码相同,直接回车)
			* 数字证书的密钥,和密钥库的密码是否相同.
			* 这项较为重要,会在tomcat配置文件中使用,建议输入与keystore的密码一致,设置其它密码也可以
		
		* OK,在~目录下,会生成 .keystore 一个证书文件
			* 至此,证书创建成功
		

	# 配置
		server.ssl.enabled=true
		server.ssl.key-store=classpath:ssl/springboot.io.p12
		server.ssl.key-store-type=PKCS12/JKS
		server.ssl.key-store-password=[key.store的密码]
	
	# http转向HTTPS
		* 很多时候,地址栏输入的http,但是会被转发到https
		* 实现这个功能需要'服务器的特定'配置来实现,就是上面说的特定配置(不同服务器用不同的)
			TomcatEmbeddedServletContainerFactory
			JettyEmbeddedServletContainerFactory

		* 代码
				@Configuration
				public class TomcatConfiguration {
					
					@Bean
					public EmbeddedServletContainerFactory embeddedServletContainerFactory(){
						TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory(){
							@Override
							protected void postProcessContext(Context context) {
								SecurityConstraint securityConstraint = new SecurityConstraint();
								securityConstraint.setUserConstraint("CONFIDENTIAL");
								SecurityCollection securityCollection = new SecurityCollection();
								securityCollection.addPattern("/*");
								securityConstraint.addCollection(securityCollection);
								context.addConstraint(securityConstraint);
							}
						};
						tomcatEmbeddedServletContainerFactory.addAdditionalTomcatConnectors(httpConnectot());
						return tomcatEmbeddedServletContainerFactory;
					}
					
					@Bean
					public Connector httpConnectot(){
						//NIO连接器
						Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
						connector.setScheme("http");
						connector.setPort(8080);            //监听端口
						connector.setSecure(false);
						connector.setRedirectPort(8443);    //转发端口
						return connector;
					}
				}


--------------------------------------
springboot配置	http2				  |
--------------------------------------
	# 必须先配置https
	# 而且目前好像只有undertow支持

		server:
		  port: 443
		  servlet:
			context-path: /
		  ssl:				//开启http2必须要开启https	
			enabled: true
			key-store: classpath:dev_ssl/javaweb.io.keystore
			key-store-type: PKCS12
			key-store-password: a12551255
		  http2:		//开启HTTP2  
			enabled: true	

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-undertow</artifactId>
		</dependency>

	# 通过谷歌浏览器查看http2是否开启成功
		chrome://net-internals/#http2