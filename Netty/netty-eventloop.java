------------------------
EventLoop				|
------------------------
	# 类库
		EventLoopGroup 
			* 继承接口:ScheduledExecutorService, Iterable
			|-MultithreadEventLoopGroup
				|-EpollEventLoopGroup
				|-NioEventLoopGroup
			

	
	# 总结
		* 一个EventLoopGroup当中会包含一个或者多个EventLoop
		* 一个EventLoop在它的整个生命周期中都只会与唯一一个Thread进行绑定
		* 所有由EventLoop所处理的各种I/O事件都将在它所关联的那个Thread上进行处理
		* 一个Channel在它的整个生命周期中只会被注册一个EventLoop上
		* 一个EventLoop在运行过程当中,会被分配给一个或者多个Cahnnel
	

