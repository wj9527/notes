
--------------------------------------
springboot配置	https				  |
--------------------------------------
	# 使用jdk系统工具生成证书
		

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


	server.http2.enabled=true    //开启HTTP2  

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