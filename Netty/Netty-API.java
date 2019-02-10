
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
				|-MessageToMessageDecoder
					|-StringDecoder
				|-ChannelInitializer
				|-SimpleChannelInboundHandler<I>

		|-ChannelOutboundHandler
			* 写处理接口

			|-ChannelOutboundHandlerAdapter
				|-MessageToByteEncoder<I>
				|-MessageToMessageEncoder<I>
					|-LengthFieldPrepender
					|-StringEncoder
		
		|-ChannelDuplexHandler
			* 同时实现了读写处理接口

			|-ChunkedWriteHandler

			

ReferenceCounted
	|-ByteBuf


	