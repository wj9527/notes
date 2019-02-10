--------------------------------
ChannelInboundHandlerAdapter	|
--------------------------------
	# class ChannelInboundHandlerAdapter extends ChannelHandlerAdapter implements ChannelInboundHandler
	# 服务端实现的读取事件处理类

--------------------------------
方法							|
--------------------------------
	public void channelActive(ChannelHandlerContext ctx)
		* 连接被建立并且准备进行通信时被调用
	
	public void channelRead(ChannelHandlerContext ctx, Object msg)
		* 读取事件

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
		* 异常时调用
	
	public void handlerAdded(ChannelHandlerContext ctx) 
		* handler被添加时调用
	
	public void handlerRemoved(ChannelHandlerContext ctx) 
		* handler被移除时调用
	
	void channelInactive(ChannelHandlerContext ctx) 

	void channelReadComplete(ChannelHandlerContext ctx)
		* 在读取完成后调用