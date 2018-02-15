------------------------
ServerSocketChannel		|
------------------------
	# ServerSocketChannel 继承 SelectableChannel ,所以它也具备设置阻塞模式,注册事件的方法
	# 它是 ServerSocket 的代替类,所以它也有 accept() 方法
	# 它不具备 public 的构造函数,只能通过静态工厂方法创建
		ServerSocketChannel.open();
	# 每个 ServerSocketChannel 对象都和一个 ServerSocket 关联,可以通过api获取
		ServerSocket socket();


------------------------
ServerSocketChannel-api	|
------------------------
	serverSocketChannel.socket();
	serverSocketChannel.setOption(null,null);
	serverSocketChannel.accept();
	serverSocketChannel.register(null,5);
	serverSocketChannel.register(null,5,null);
	serverSocketChannel.bind(null);
	serverSocketChannel.bind(null,5);
	serverSocketChannel.getLocalAddress();
	serverSocketChannel.validOps();
	serverSocketChannel.blockingLock();
	serverSocketChannel.close();
	serverSocketChannel.getOption(null);
	serverSocketChannel.isBlocking();
	serverSocketChannel.isOpen();
	serverSocketChannel.isRegistered();
	serverSocketChannel.keyFor(null);
	serverSocketChannel.supportedOptions();
	serverSocketChannel.provider();
	serverSocketChannel.configureBlocking(false);