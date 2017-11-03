------------------------
WebSocket-js			|
------------------------
	# 很显然这是JS的东西
	# 检测浏览器是否支持WebSocket
		function loadDemo() {  
			if (window.WebSocket) {  
				//支持  
			} else {  
				//不支持
			}  
		}  
	

------------------------
WebSocket-WebSocket		|
------------------------
	# 创建 WebSocket 对象
		var Socket = new WebSocket(url,[protocol]);
			* url:表示连接的地址,通常以协议:"ws://"或者"wss://"(安全的websocket)协议开头
			* protocol:是一个数组,可以接受一个或者多个子协议,该参数并不是必须的
	
	# 属性
		readyState	
			* 只读属性 readyState 表示连接状态，可以是以下值
			CONNECTING	:值为0,表示正在连接
			OPEN		:值为1,表示连接成功，可以通信了
			CLOSING		:值为2,表示连接正在关闭
			CLOSED		:值为3,表示连接已经关闭,或者打开连接失败
			
		bufferedAmount	
			* 只读属性 bufferedAmount 已被 send() 放入正在队列中等待传输,但是还没有发出的 UTF-8 文本字节数.
			* 可以判断消息是否发送完毕
				if (socket.bufferedAmount === 0) {
				  // 发送完毕
				} else {
				  // 发送还没结束
				}
		
		binaryType 
			* 指定二进制数据类型
			* 枚举值(字符串)
				binaryType = "blob";
				binaryType = "arraybuffer";
			
	# 事件
		* 事件处理函数,都会有一个 event 参数
			Socket.onopen	
				* 连接建立时触发
				*  event属性
					var protocol = event.target.protocol

			Socket.onmessage	
				* 客户端接收服务端数据时触发
				* event属性
					var data = event.data;

				* 判断消息类型
					if(typeof event.data === String) {
						console.log("Received data string");
					}
					if(event.data instanceof ArrayBuffer){
						console.log("Received arraybuffer");
					}

			Socket.onerror	
				* 通信发生错误时触发

			Socket.onclose	
				* 连接关闭时触发
				* event属性
					var code = event.code;
					var reason = event.reason;
					var wasClean = event.wasClean;

		* event通用属性
			
	
	# 方法
		Socket.send()	
			* 使用连接发送数据
			* 发送二进制数据
				var file = document.querySelector('input[type="file"]').files[0];
				ws.send(file);

		Socket.close()	
			* 关闭连接
			* 在窗口关闭之前,就关闭到socket连接,有可能会导致服务器端异常
				window.onbeforeunload = function () {  
					ws.close();  
				}  
		

------------------------
WebSocket-WebSocket		|
------------------------
