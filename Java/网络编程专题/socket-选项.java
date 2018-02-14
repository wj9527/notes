----------------------------
socket选项					|
----------------------------
	SocketOptions 常量
		TCP_NODELAY
			* 是否立即发送数据
		SO_RESUSEADDR
			* 是否允许重用socket所绑定的本地地址
		SO_TIMEOUT
			* 表示接收数据时的,等待超时时间
		SO_LINGER
			* 表示当执行Socket的close()方法时,是否立即关闭底层的socket
		SO_RCVBUF
			* 表示接收数据的缓冲区大小
		SO_SNDBUF
			* 表示发送数据的缓冲区大小
		SO_KEEPALIVE
			* 表示对于长时间处于空闲状态的Socket是否要自动把它关闭
		OOBINLINE
			* 表示是否支持发送一个字节的TCP紧急数据

----------------------------
socket选项-详解				|
----------------------------
	TCP_NODELAY
		* 默认值为 false,表示才用Negale算法
		* socket api
			void setTcpNoDelay(boolean on) 
			boolean getTcpNoDelay()
		
		* 默认情况下,发送数据采用Negale算法,该算法指的是:发送方发送的数据 不会立即发出,而是先放在缓冲区内
		  缓冲区满了再发出,发送完一批数据后,会等待接收方对这批数据回应,然后再发送下一批数据
		* 这种算法通过减少传输数据的次数来提高通信效率,适用于:发送方需要发送大批量的数据,并且接收方会及时做出回应的场合
		* 如果发送方持续地发送小批量数据,并且接收方不一定会立即响应数据,那么Negale算法会使发送方运行慢(游戏,鼠标的移动都会发送数据到服务器,就不要才用Negale算法)
		* 如果socket底层不支持 TCP_NODELAY 会抛出 SocketException
	
	
	SO_RESUSEADDR
		* 默认值为 false,表示不允许地址重用
		* socket api
			 void setReuseAddress(boolean on)
			 boolean getReuseAddress()
		
		* 当 Socket 执行 close()关闭的时候,如果网络上还有发送到这个Socket的数据,那么底层的Socket不会立即释放本地端口,而是会等待一段时间
		  确保收到了网络上发过来的延迟数据,然后再释放端口,Socket 收到这些延迟数据后,不会做任何处理,目的是:确保这些数据不会被其他碰巧绑定到同样端口的新进程接收到
		* 为了确保一个进程关闭socket后,即使它还没释放端口,同一个主机上的其他进程还可以即刻重用该端口,可以调用api设置 true
		* 需要注意的是,setReuseAddress(true),必须在Socket还没有绑定到一个本地端口之前调用,否则无效
		* 公用同一个端口的进程都必须调用:setReuseAddress(true)
		  
	SO_TIMEOUT

