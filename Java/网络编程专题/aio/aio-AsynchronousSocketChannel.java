--------------------------------
AsynchronousSocketChannel-api	|
--------------------------------
	static AsynchronousSocketChannel open()
	static AsynchronousSocketChannel open(AsynchronousChannelGroup group)
	
	Future<Integer> read(ByteBuffer dst);
	void read(ByteBuffer dst,A attachment,CompletionHandler<Integer,? super A> handler)
	void read(ByteBuffer dst,long timeout,TimeUnit unit, A attachment,CompletionHandler<Integer,? super A> handler)
	void read(ByteBuffer[] dsts,int offset,int length,long timeout,TimeUnit unit,A attachment,CompletionHandler<Long,? super A> handler)

	Future<Integer> write(ByteBuffer src)
	void write(ByteBuffer src,A attachment,CompletionHandler<Integer,? super A> handler)
	void write(ByteBuffer src,long timeout,TimeUnit unit,A attachment,CompletionHandler<Integer,? super A> handler)
	void write(ByteBuffer[] srcs,int offset,int length,long timeout,TimeUnit unit,A attachment,CompletionHandler<Long,? super A> handler)
	
	Set<SocketOption<?>> supportedOptions()
	AsynchronousSocketChannel setOption(SocketOption<T> name, T value)
	T getOption(SocketOption<T> name)

	SocketAddress getLocalAddress()
	SocketAddress getRemoteAddress()

	AsynchronousSocketChannel shutdownInput()
	AsynchronousSocketChannel shutdownOutput()

	Future<Void> connect(SocketAddress remote)
	void connect(SocketAddress remote,A attachment,CompletionHandler<Void,? super A> handler)

	void close()
	AsynchronousSocketChannel bind(SocketAddress local)
	AsynchronousChannelProvider provider()
	boolean isOpen()
