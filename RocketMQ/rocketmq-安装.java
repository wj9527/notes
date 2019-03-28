
# 单机安装
	# 下载,解压
	# 配置环境变量
		export ROCKETMQ_HOME=/usr/local/rocketmq/alibaba-rocketmq

	# 启动nameserver
		bin/mqnamesrv.sh
	
	# 启动broker
		bin/mqbroker.sh

			-n 
				* 参数指定绑定的网卡和端口
				-n 0.0.0.0:9876
	
			