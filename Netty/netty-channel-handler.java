-----------------------------
ChannelHandler				 |
-----------------------------
	# 提供基本的Handler事件
	# 类库
		ChannelHandler
			|-ChannelHandlerAdapter
			|-ChannelInboundHandler
			|-ChannelOutboundHandler

	# Handler的生命周期(基本的事件)
		handlerAdded	ChannelHandler 添加到 ChannelPipeline
		handlerRemoved	ChannelHandler 从 ChannelPipeline 移除
		exceptionCaught	ChannelPipeline 执行抛出异常

-----------------------------
ChannelInboundHandler		 |
-----------------------------
	# 读取Handler
	# 类库
		|-ChannelInboundHandler
			|-ChannelInboundHandlerAdapter
				* 它实现了 ChannelInboundHandler 的所有方法
				* 默认的作用就是处理消息(事件)并将消息转发到 ChannelPipeline 中的下一个 ChannelHandler

				|-ByteToMessageDecoder
					|-ReplayingDecoder<S> 
					|-LineBasedFrameDecoder
					|-LengthFieldBasedFrameDecoder
					|-DelimiterBasedFrameDecoder
					|-FixedLengthFrameDecoder
					|-SslHandler
				|-MessageToMessageDecoder
					|-StringDecoder
				|-ChannelInitializer
				|-SimpleChannelInboundHandler<I>

	# 事件(接口方法)
		channelRegistered	channel	注册到一个 EventLoop,该状态可以出现多次,可以重复的注册取消注册
		channelActive		channel	变为活跃状态(连接到了远程主机),现在可以接收和发送数据了,该状态只会出现一次
		channelInactive		channel	处于非活跃状态(连接已经关闭),没有连接到远程主机,该状态只会出现一次
		channelUnregistered	channel	已创建但未注册到一个 EventLoop	(或者从EventLoop中移除),该状态可以出现多次,可以重复的注册取消注册

		channelReadComplete			channel 读取完成
		channelRead					channel 可以读取
		userEventTriggered			channel 用户自定义事件
		channelWritabilityChanged	channel 可写状态改变,可以使用 Channel.isWritable()检查
		exceptionCaught				channel 异常事件

	# SimpleChannelInboundHandler<T>
		* 抽象类,需要覆写抽象方法: channelRead0(ChannelHandlerContext ctx, T msg)
		* 自动强制转换类型,并且自动的释放buf资源
			 ReferenceCountUtil.release(msg);
	
	# 一般使用的和场景
		ChannelInboundHandlerAdapter	处理其事件或者状态改变
		SimpleChannelInboundHandler		处理消息


-----------------------------
ChannelOutboundHandler		 |
-----------------------------
	# 写入Handler
	# 类库
		|-ChannelOutboundHandler
			|-ChannelOutboundHandlerAdapter
				|-MessageToByteEncoder<I>
				|-MessageToMessageEncoder<I>
					|-LengthFieldPrepender
					|-StringEncoder

-----------------------------
ChannelPromise 机制			 |
-----------------------------
	# ChannelPromise
		* 它继承自ChannelFuture
	
	# 运行
		* 特殊的 ChannelFuture,允许你的 ChannelPromise 及其 操作 成功或失败
		* 所以任何时候调用例如 Channel.write(...) 一个新的 ChannelPromise 将会创建并且通过 ChannelPipeline 传递
		* 这次写操作本身将会返回 ChannelFuture, 这样只允许你得到一次操作完成的通知
		* Netty 本身使用 ChannelPromise 作为返回的 ChannelFuture 的通知,事实上在大多数时候就是 ChannelPromise 自身
		* ChannelPromise 扩展了 ChannelFuture
		* 如前所述,ChannelOutboundHandlerAdapter 提供了一个实现了 ChannelOutboundHandler 所有基本方法的实现的框架。
		* 这些简单事件转发到下一个 ChannelOutboundHandler 管道通过调用 ChannelHandlerContext 相关的等效方法,可以根据需要自己实现想要的方法


-----------------------------
共享的Handler				 |
-----------------------------
	# ChannelHandler 实例如果带有 @Sharable 注解则可以被添加到多个ChannelPipeline
		* 也就是说单个 ChannelHandler 实例可以有多个 ChannelHandlerContext
		* 因此可以调用不同 ChannelHandlerContext 获取同一个 ChannelHandler 
		* 如果添加不带 @Sharable 注解的 ChannelHandler 实例到多个 ChannelPipeline 则会抛出异常
		* 使用 @Sharable 注解后的 ChannelHandler 必须在不同的线程和不同的通道上安全使用
	
	# 使用 @Sharable 注解共享一个 ChannelHandler 在一些需求中还是有很好的作用的
		* 如使用一个 ChannelHandler 来统计连接数或来处理一些全局数据等等

