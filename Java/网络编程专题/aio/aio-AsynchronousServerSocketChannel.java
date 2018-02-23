





----------------------------------------
AsynchronousServerSocketChannel-api		|
----------------------------------------
	static AsynchronousServerSocketChannel open()
	static AsynchronousServerSocketChannel open(AsynchronousChannelGroup group)
	
	Future<AsynchronousSocketChannel> accept();
	void accept(A attachment,CompletionHandler<AsynchronousSocketChannel,? super A> handler);

	AsynchronousServerSocketChannel bind(SocketAddress local)
	AsynchronousServerSocketChannel bind(SocketAddress local, int backlog)
	
	Set<SocketOption<?>> supportedOptions();
	AsynchronousServerSocketChannel setOption(SocketOption<T> name, T value)
	T getOption(SocketOption<T> name)

	void close()
	boolean isOpen();
	SocketAddress getLocalAddress() 
	AsynchronousChannelProvider provider()
	
