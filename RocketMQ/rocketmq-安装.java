
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
	
		
		mqbroker.cmd -n localhost:9876 autoCreateTopicEnable=true
	
		
		# 启动异常修改脚本:runbroker.cmd

			set CLASSPATH=.;%BASE_DIR%conf;"%CLASSPATH%"



## 默认端口
mqnamesrv:9876
mqbroker - mqnamesrv:10909
mqbroker - client:10911

## 启动
### 启动namesrc
nohup ./bin/mqnamesrv -n "0.0.0.0:9876" > /dev/null &

### 修改配置文件，设置本机的ip可以被生产者，消费者访问到
echo 'brokerIP1=192.168.0.3' > conf/broker.properties

### 启动broker 
nohup ./bin/mqbroker -n localhost:9876 -c conf/broker.properties autoCreateTopicEnable=true > /dev/null &