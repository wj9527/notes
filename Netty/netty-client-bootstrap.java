------------------------
Bootstrap				|
-----------------------
	# 客户端一般配置代码
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.SO_REUSEADDR, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					
				}
			});
			ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 1024)).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}