-----------------------------
ssl							 |
-----------------------------
	#　一个使用 SslHandler 数据流程
		* 加密的入站数据被 SslHandler 拦截,并被解密为平常数据
		* 平常数据传过 SslHandler
		* SslHandler 加密数据并它传递出站
	
	# 在大多数情况下,SslHandler 将成为 ChannelPipeline 中的第一个 ChannelHandler 
		* 这将确保所有其他 ChannelHandler 应用他们的逻辑到数据后加密后才发生,从而确保他们的变化是安全的
	
	# 涉及类库
		SslHandler