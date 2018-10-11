------------------------
Pipe 管道				|
------------------------
	# 管道流
		*  可以往一头流写入数据,在另一头读取数据
		* 一般用在多线程环境下,有点类似于生产者消息费者的模型

	# 打开管道
		Pipe pipe = Pipe.open();

	# 从管道获取写流
		SinkChannel sinkChannel = pipe.sink();
	
	# 从管道获取读流
		SourceChannel sourceChannel = pipe.source();
	
	# Demo
		import java.io.IOException;
		import java.nio.ByteBuffer;
		import java.nio.channels.Pipe;
		import java.nio.channels.Pipe.SinkChannel;
		import java.nio.channels.Pipe.SourceChannel;
		import java.nio.charset.StandardCharsets;
		import java.util.Scanner;

		public class Main {
			
			public static void main(String[] args) throws Exception {
				
				Pipe pipe = Pipe.open();
				
				SinkChannel sinkChannel = pipe.sink();
				
				SourceChannel sourceChannel = pipe.source();
				new Thread(() -> {
					try {
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						while(true) {
							sourceChannel.read(byteBuffer);
							byteBuffer.flip();
							byte bytes[] = new byte[byteBuffer.remaining()]; 
							byteBuffer.get(bytes);
							System.out.println(Thread.currentThread().getName() + "-收到消息:" + new String(bytes,StandardCharsets.UTF_8));
							byteBuffer.clear();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}) .start();
			
				try(Scanner scanner = new Scanner(System.in)){
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					while(true) {
						String line = scanner.nextLine();
						System.out.println(Thread.currentThread().getName() + "-发送消息:" + line);
						byteBuffer.put(line.getBytes(StandardCharsets.UTF_8));
						byteBuffer.flip();
						sinkChannel.write(byteBuffer);
						byteBuffer.clear();
					}
				}
			}
		}