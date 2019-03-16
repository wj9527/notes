------------------------
ssl						|
------------------------
	# SSL
		* netscape 公司提出,Server Socket Layer
	
	# Java的安全套接字扩展 JSSE(Java Secure Socket Extension)
		* 为基于SSL和TLS协议的Java网络应用提供了Java API和参考实现
		* 支持数据加密,服务端身份严重,数据完整性,以及可选的客户端身份严重
	
	# TLS/SSL
		* 目前来说TSL1.0和SSL3.0差别非常小
	


	# 类库
		javax.net
		javax.net.ssl
		java.security.cert

		KeyStore

		KeyManagerFactory
		KeyManager

		TrustManagerFactory
		TrustManager

		SecureRandom

		SSLContext
		
		SSLServerSocketFactory
		SSLServerSocket(ServerSocket子类)

		SSLSocketFactory
		SSLSocket(Socket子类)

		SSLEngine

		SSLSession

		X509Certificate