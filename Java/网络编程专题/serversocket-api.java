-----------------------
ServerSocket - ¹¹Ôìº¯Êý|		
-----------------------
	 ServerSocket() throws IOException
	 ServerSocket(int port)
	 ServerSocket(int port, int backlog)
	 ServerSocket(int port, int backlog, InetAddress bindAddr)

-----------------------
ServerSocket - api	   |		
-----------------------
	 static synchronized void setSocketFactory(SocketImplFactory fac)

	 Socket accept()
	 void bind(SocketAddress endpoint)
	 void bind(SocketAddress endpoint, int backlog)
	 void close()
	 ServerSocketChannel getChannel()
	 InetAddress getInetAddress()
	 int getLocalPort()
	 SocketAddress getLocalSocketAddress()
	 int getReceiveBufferSize()
	 boolean getReuseAddress()
	 int getSoTimeout()
	 boolean isBound()
	 boolean isClosed()
	 void setPerformancePreferences(int connectionTime,int latency,int bandwidth)
	 void setReceiveBufferSize (int size)
	 void setReuseAddress(boolean on)
	 void setSoTimeout(int timeout)


	