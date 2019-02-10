
--------------------
handler体系			|
--------------------
	ChannelHandler
		* 最顶层hanlder

		|-ChannelHandlerAdapter
		|-ChannelInboundHandler
			* 读处理接口

			|-ChannelInboundHandlerAdapter
				|-ByteToMessageDecoder
					|-ReplayingDecoder<S> 
					|-LineBasedFrameDecoder
					|-LengthFieldBasedFrameDecoder
					|-DelimiterBasedFrameDecoder
					|-FixedLengthFrameDecoder
				|-ChannelInitializer
				|-SimpleChannelInboundHandler<I>
		|-ChannelOutboundHandler
			* 写处理接口

			|-ChannelOutboundHandlerAdapter
				|-MessageToByteEncoder<I>

ReferenceCounted
	|-ByteBuf


	