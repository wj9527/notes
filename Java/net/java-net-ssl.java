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
			* 密钥证书存储设施
			* 这个对象用于存放安全证书,安全证书一般以文件形式存放
			* KeyStore 负责将证书加载到内存

		KeyManagerFactory
		KeyManager
			* 密钥管理器
			* 责选择用于证实自己身份的安全证书,发给通信另一方
			* KeyManager 对象由 KeyManagerFactory 工厂类生成

		TrustManagerFactory
		TrustManager
			* 信任管理器
			* 负责判断决定是否信任对方的安全证书
			* TrustManager 对象由 TrustManagerFactory 工厂类生成

			|-X509Certificate
				* 接口的实现
				

		SSLContext
			* 对整个SSL/TLS协议的封装,表示了安全套接字协议的实现
			* 主要负责设置安全通信过程中的各种信息,例如跟证书相关的信息
			* 并且负责构建 SSLSocketFactory,SSLServerSocketFactory 和 SSLEngine 等工厂类

		SSLServerSocketFactory
		SSLServerSocket(ServerSocket子类)

		SSLSocketFactory
		SSLSocket(Socket子类)

		SSLEngine
			* SSL非阻塞引擎
			* NIO通信,使用这个类,它让通过过程支持非阻塞的安全通信

		SSLSession
			* SSL会话
			* 安全通信握手过程需要一个会话,为了提高通信的效率
			* SSL协议允许多个SSLSocket共享同一个SSL会话,在同一个会话中,只有第一个打开的 SSLSocket 需要进行SSL握手,负责生成密钥及交换密钥,其余SSLSocket都共享密钥信息

		
			

		SecureRandom