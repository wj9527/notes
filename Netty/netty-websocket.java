---------------------------------
websocket						 |
---------------------------------
	# 类库
		WebSocketFrame
			* ws的数据类型
			|-BinaryWebSocketFrame			
			*  二进制数据
			|-CloseWebSocketFrame
			* 关闭通道
			|-ContinuationWebSocketFrame
			* 消息分片
			|-PingWebSocketFrame
			* ping
			|-PongWebSocketFrame
			* pong
			|-TextWebSocketFrame
			* 文本数据
	
	
---------------------------------
服务端							 |
---------------------------------
import java.util.Locale;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup);
			serverBootstrap.channel(NioServerSocketChannel.class);
			serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					ChannelPipeline pipeline = socketChannel.pipeline();
					pipeline.addLast(new HttpServerCodec());
					pipeline.addLast(new HttpObjectAggregator(65536));
					pipeline.addLast(new WebSocketServerCompressionHandler());
					pipeline.addLast(new WebSocketServerProtocolHandler("/websocket", null, true));	// uri,子协议,是否支持扩展
					pipeline.addLast(new SimpleChannelInboundHandler<WebSocketFrame>() {
						@Override
						protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
							if (msg instanceof TextWebSocketFrame) { // 文本消息处理
								String request = ((TextWebSocketFrame) msg).text();
								System.out.println("服务端收到消息:" + request);
								ctx.channel().writeAndFlush(new TextWebSocketFrame(request.toUpperCase(Locale.US)));
							} else {
								String message = "不支持的消息类型: " + msg.getClass().getName();
								throw new UnsupportedOperationException(message);
							}
						}
					});
				}
			});
			Channel channel = serverBootstrap.bind(1024).sync().channel();
			channel.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
