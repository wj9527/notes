---------------------------------
websocket						 |
---------------------------------
	# 类库
		WebSocketFrame
			|-BinaryWebSocketFrame			
			|-CloseWebSocketFrame
			|-ContinuationWebSocketFrame
			|-PingWebSocketFrame
			|-PongWebSocketFrame
			|-TextWebSocketFrame
		WebSocketServerProtocolHandler
		WebSocketServerCompressionHandler

	# WebSocketFrame
		* ws消息类型的抽象类,提供了N个实现,表示不同的消息类型
		* 看子类,类名就知道是啥消息的实现了
	
	# WebSocketServerCompressionHandler
		* 提供了对websocket消息的压缩
		* 私有化了构造函数,提供了一个全局的静态实例
			WebSocketServerCompressionHandler.INSTANCE
		* 使用 @ChannelHandler.Sharable 标识,可以用于多个Channel

-------------------------------------
WebSocketServerProtocolHandler		 |
-------------------------------------
	# 负责处理:握手,控制帧(ping/pong/close),文本消息,二进制消息....
	# 构造函数
		WebSocketServerProtocolHandler(String websocketPath)
		WebSocketServerProtocolHandler(String websocketPath, boolean checkStartsWith)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions) 
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions, int maxFrameSize)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions, int maxFrameSize, boolean allowMaskMismatch)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions, int maxFrameSize, boolean allowMaskMismatch, boolean checkStartsWith)
		WebSocketServerProtocolHandler(String websocketPath, String subprotocols,boolean allowExtensions, int maxFrameSize, boolean allowMaskMismatch, boolean checkStartsWith, boolean dropPongFrames)
		
		websocketPath
			* 提供服务的uri
		subprotocols
			* 支持的子协议列表
		allowExtensions
			* 是否允许扩展
		maxFrameSize
			* 消息帧最大字节数
		allowMaskMismatch
		checkStartsWith
		dropPongFrames
	
	# 它会把websocket的数据编码为 WebSocketFrame 对象,交给下一个Handler去处理

	
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
import io.netty.handler.stream.ChunkedWriteHandler;

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
					pipeline.addLast(new ChunkedWriteHandler());
					pipeline.addLast(new HttpObjectAggregator(65536));
					pipeline.addLast(new WebSocketServerCompressionHandler());
					pipeline.addLast(new WebSocketServerProtocolHandler("/websocket", null, true));	// uri,子协议,是否支持扩展
					pipeline.addLast(new SimpleChannelInboundHandler<WebSocketFrame>() {
						@Override
						public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
							String id = ctx.channel().id().asLongText();
							System.out.println("新的连接:" + id);
							super.handlerAdded(ctx);
						}
						@Override
						public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
							System.out.println("连接断开:" + ctx.channel().id().asLongText());
							super.channelInactive(ctx);
						}
						@Override
						protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
							if (msg instanceof TextWebSocketFrame) { // 文本消息处理
								String request = ((TextWebSocketFrame) msg).text();
								System.out.println("服收到消息:" + request);
								ctx.channel().writeAndFlush(new TextWebSocketFrame(request.toUpperCase(Locale.CHINA)));
							} else {	// 其他类型的消息
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
