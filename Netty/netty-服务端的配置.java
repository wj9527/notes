----------------------------
Netty服务端的配置			|
----------------------------
	// 处理连接的线程池
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	// 处理IO事件的线程池
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	try {
		// 创建服务端对象
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		// 设置线程池
		serverBootstrap.group(bossGroup, workerGroup);
		// 设置io模式
		serverBootstrap.channel(NioServerSocketChannel.class);
		// 设置日志handler
		serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
		// 设置客户端handlder
		serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			// 初始化信息设置
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new TimeServerHandler());
			}
		});
		// 设置服务端属性
		serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
		serverBootstrap.option(ChannelOption.SO_REUSEADDR, true);

		// 设置客户端属性
		serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

		// 绑定端口,并且同步(阻塞)的启动服务
		ChannelFuture channelFuture = serverBootstrap.bind(1024).sync();
		channelFuture.channel().closeFuture().sync();
	} finally {
		bossGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}