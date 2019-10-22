------------------------------
安装						  |
------------------------------

------------------------------
Linux安装					  |
------------------------------
	# 下载指定的版本
		https://www.elastic.co/cn/downloads/elasticsearch
	
	# 解压到目录

	# 创建运行用户, 执行目录授权
		* ES不允许直接使用root账户进行启动, 会给出异常:can not run elasticsearch as root

		useradd -r elasticsearch

		chown elasticsearch [path] -R

		* 如果指定了其他的志数据目录, 也需要进行授权

	
	# 启动脚本
		/bin/elasticsearch

		-E

	

