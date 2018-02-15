----------------------------
SocketChannel 静态方法		|
----------------------------
	static SocketChannel open()
	SocketChannel open(SocketAddress remote)

----------------------------
SocketChannel 实例方法		|
----------------------------
	SocketChannel bind(SocketAddress local)
	boolean connect(SocketAddress remote)
	boolean finishConnect()
	SocketAddress getLocalAddress()
	SocketAddress getRemoteAddress()
	boolean isConnected()
	boolean isConnectionPending()

	int read(ByteBuffer dst)
	long read(ByteBuffer[] dsts)
	long read(ByteBuffer[] dsts, int offset, int length)

	<T> SocketChannel setOption(SocketOption<T> name, T value)
	SocketChannel shutdownInput()
	SocketChannel shutdownOutput()
	Socket socket()
	int validOps()

	write(ByteBuffer src)
	long write(ByteBuffer[] srcs)
	long write(ByteBuffer[] srcs, int offset, int length)
