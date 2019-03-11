---------------------
kafka-入门			 |
---------------------
	# 官网
		http://kafka.apache.org
	
	# 中文文档
		http://kafka.apachecn.org/intro.html

	# 参考
		https://blog.csdn.net/lizhitao/article/details/39499283
		http://www.jasongj.com/tags/Kafka/

	
	# 特点
		* 吞吐量高,在廉价的机器上,单机可以支持100w/s消息的读写
		* 消息持久化,所有消息都会被持久化到硬盘,无消息丢失,支持消息重放
		* 完全分布式:Producer,Broker,Consumer都支持水平的扩展
		* 同时满足适应在线流处理和离线批处理
		
		
---------------------
kafka-目录结构		 |
---------------------
	bin
		windows
			kafka-run-class.bat
			kafka-server-start.bat
			kafka-server-stop.bat
		kafka-run-class.sh
		kafka-server-start.sh
		kafka-server-stop.sh
	config
		connect-console-sink.properties
		connect-console-source.properties
		connect-distributed.properties
		connect-file-sink.properties
		connect-file-source.properties
		connect-log4j.properties
		connect-standalone.properties
		consumer.properties
		log4j.properties
		producer.properties
		server.properties
		tools-log4j.properties
		trogdor.conf
		zookeeper.properties
	libs
	site-docs
		kafka_2.12-2.1.1-site-docs.tgz