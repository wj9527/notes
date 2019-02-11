------------------------
消息的编解码			|
------------------------
	# 涉及类库
		|-ChannelDuplexHandler
			|-CombinedChannelDuplexHandler
	
	# 结合解码器和编码器在一起可能会牺牲可重用性