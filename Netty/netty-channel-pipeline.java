----------------------------
ChannelPipeline				|
---------------------------
	# 继承:ChannelInboundInvoker ChannelOutboundInvoker
	# 一个 ChannelPipeline 是用来保存关联到一个 Channel 的ChannelHandler
		* 可以修改 ChannelPipeline 通过动态添加和删除 ChannelHandler
		* ChannelPipeline 有着丰富的API调用动作来回应入站和出站事件
	
	
	# 它也可以给客户端响应数据
		ChannelPipeline pipeline = ctx.pipeline();
		pipeline.write(Unpooled.copiedBuffer("netty in action", CharsetUtil.UTF_8));

		* 该api处理消息从 ChannelPipeline 的尾部开始,也是从头开始进入整个输出执行链