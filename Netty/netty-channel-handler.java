-----------------------------
Handler						 |
-----------------------------
	# Handler的生命周期
		handlerAdded	ChannelHandler 添加到 ChannelPipeline
		handlerRemoved	ChannelHandler 从 ChannelPipeline 移除
		exceptionCaught	ChannelPipeline 执行抛出异常时调用

-----------------------------
ChannelPromise 机制			 |
-----------------------------
	* 特殊的 ChannelFuture,允许你的 ChannelPromise 及其 操作 成功或失败
	* 所以任何时候调用例如 Channel.write(...) 一个新的 ChannelPromise将会创建并且通过 ChannelPipeline传递
	* 这次写操作本身将会返回 ChannelFuture, 这样只允许你得到一次操作完成的通知
	* Netty 本身使用 ChannelPromise 作为返回的 ChannelFuture 的通知,事实上在大多数时候就是 ChannelPromise 自身
	* ChannelPromise 扩展了 ChannelFuture
	* 如前所述,ChannelOutboundHandlerAdapter 提供了一个实现了 ChannelOutboundHandler 所有基本方法的实现的框架。
	* 这些简单事件转发到下一个 ChannelOutboundHandler 管道通过调用 ChannelHandlerContext 相关的等效方法,可以根据需要自己实现想要的方法

