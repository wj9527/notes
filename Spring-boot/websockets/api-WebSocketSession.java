-----------------------------
WebSocketSession
-----------------------------
	# session会话接口
	
	# 抽象方法
		String getId();

		@Nullable
		URI getUri();

		HttpHeaders getHandshakeHeaders();

		Map<String, Object> getAttributes();

		@Nullable
		Principal getPrincipal();

		@Nullable
		InetSocketAddress getLocalAddress();

		@Nullable
		InetSocketAddress getRemoteAddress();

		@Nullable
		String getAcceptedProtocol();

		void setTextMessageSizeLimit(int messageSizeLimit);

		int getTextMessageSizeLimit();

		void setBinaryMessageSizeLimit(int messageSizeLimit);

		int getBinaryMessageSizeLimit();

		List<WebSocketExtension> getExtensions();

		void sendMessage(WebSocketMessage<?> message) throws IOException;

		boolean isOpen();

		@Override
		void close() throws IOException;

		void close(CloseStatus status) throws IOException;