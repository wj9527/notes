-----------------------
安全课-网络数据抓包		|
-----------------------
	# 网络数据抓包与分析
		* 在做网络编程,开发的时候,用来测试.检查
		* 

-----------------------
安全课-Wireshark		|
-----------------------
	# 非常牛逼的一个抓包工具,Linux,Windos,MAC系统下都有
	# 可以通过这个工具抓取数据包
	Interface List
		* 显示当前有哪些网卡
	
	1,选择网卡
		
	2,设置抓包过滤器
		* 抓取范围(ip)
		* 语法: protocol $ Direction $Host(s) $ Value $ Logical Operation $ Other expression
			protocol
				* 协议,可能的值:eher,fddi,ip,arp,rarp,decnet,lat,sca,moprc,mopdl,tcp,udp

			Direction
				* 方向,可能的值:src,dst,src and dst,src or dst
				* 发送包的源是谁
				* dst-目标
				* src-源

			$Host				
				* 可能的值:net,port,host,portrange
				* 如果没有指定该值,默认使用'Host'关键字,例如:'src 10.1.1.1'与'src host 10.1.1.1'相同

			Value				
				
			Logical Operation	
				* 逻辑运算,可以能的值:not ,and ,or
				* not,也就是否,具有最高优先级.或(or),和与(and),具有相同优先级,运算时,从左至右运行
				* 'not tcp port 3128 and tcp prot 23' 与 '(not tcp port 3128) and tcp port 23'相同
				* 'not tcp port 3128 and tcp port 23' 与 'not (tcp port 3128 and tcp port 23)'不同

			Other expression
				* 别的选项

		


	3,设置显示过滤器
		* 抓取的哪些包(HTTP,HTTPS,TCP,UDP)
			* HTTP(POST/GET)
		* 语法:Protocol.String1.String2 $ Comparison operator $ Value $ Logical Operations $ Other expression
		* 例子:
			ip src == 192.168.1.100		//显示来源为:192.168.1.100的包
			tcp dst port 8080

			
			http.request.method==GET	//获取POST的请求



	4,网络封包数据分析
		* 帐号,密码


-----------------------
安全课-Wireshark		|
-----------------------
	Ethernet II 
		* 以太网协议

	Internet Protocol Version 4(IP)
		* IP协议(封装了源IP,目标IP)
		Source		:发送数据包的人
		Destination	:目标IP

	Hypertext Transfer Protocol(TCP)
		* 应用层协议(封装了端口号,TTL,应答序列... ...)
		Source Port			:源端口
		Destination Port	:目标端口
		
		