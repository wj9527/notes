-------------------------
socket-UDP(BIO)			 |
-------------------------
	import socket
	# 创建socket实例,type=socket.SOCK_DGRAM
	client = socket.socket(type=socket.SOCK_DGRAM)
	# 发送UDP包到指定的地址
	client.sendto(b'Hello,Python',('localhost',1024))
	

	import socket
	# 创建socket实例,type=socket.SOCK_DGRAM
	server = socket.socket(type=socket.SOCK_DGRAM)
	# 绑定本机地址 & 监听端口
	server.bind(('localhost',1024))
	# 准备接收包
	while True:
		# 收到数据包
		client = server.recvfrom(64)
		print(client[0])    # 数据包
		print(client[1])    # 客户端IP & 端口

-------------------------
socket-TCP(BIO)			 |
-------------------------
	import socket
	# 创建socket实例.默认地址簇与协议:family=AF_INET type=SOCK_STREAM	
	client = socket.socket(family=AF_INET,type=SOCK_STREAM)
	# 设置连接信息
	client.connect(('localhost',1024))
	# 发送消息到目标服务器
	client.send("Hello Python".encode(encoding='utf_8', errors='strict'))
	#client.sendall("Hello Python".encode(encoding='utf_8', errors='strict'))
	# 读取目标服务器响应的数据
	response = client.recv(99999).decode(encoding='utf_8', errors='strict')
	print(response)


	import socket
	# 创建socket实例
	server = socket.socket(family=AF_INET,type=SOCK_STREAM)
	# 绑定本机地址 & 监听端口
	server.bind(('localhost',1024))
	# 最大连5,超过5就排队
	server.listen(5)
	while True:
		# 新的客户端连接
		client = server.accept()
		# 读取客户端数据
		request = client[0].recv(1024)
		# 响应客户端数据
		client[0].send("你好客户端".encode(encoding='utf_8', errors='strict'))
		print(request)

