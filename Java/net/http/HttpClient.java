-------------------------
HttpClient
-------------------------
	# 抽象类

	# 静态方法
		 public static HttpClient newHttpClient()
		 public static Builder newBuilder()
	
	# 抽象方法
		Optional<CookieHandler> cookieHandler()
		abstract Optional<Duration> connectTimeout()
		abstract Redirect followRedirects()
			* 重定向策略枚举
				NEVER
					* 永远也不重定向

				ALWAYS
					* 总是自动重定向

				NORMAL
					* 除了从HTTPS重定向到HTTP以外, 都会自动重定向


		abstract Optional<ProxySelector> proxy()
		abstract SSLContext sslContext()
		abstract SSLParameters sslParameters()
		abstract Optional<Authenticator> authenticator()
		abstract HttpClient.Version version()
			* 枚举HTTP版本
				HTTP_1_1
				HTTP_2

		abstract Optional<Executor> executor()
		abstract <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler)
		abstract <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, BodyHandler<T> responseBodyHandler)
		abstract <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, BodyHandler<T> responseBodyHandler, PushPromiseHandler<T> pushPromiseHandler)
	
	# 实例方法
		WebSocket.Builder newWebSocketBuilder()