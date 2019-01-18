------------------------
4版本的安装				|
------------------------
1,官网下载
	https://redis.io/
2,解压
3,进入解压目录
4,指定安装目录,编译,安装
	make PREFIX=/usr/local/redis install
	* 注意:PREFIX是大写
	* 会在指定目录下生成目录
	bin
		redis-benchmark
		redis-check-aof
		redis-check-rdb
		redis-cli
		redis-sentinel -> redis-server
		redis-server
5,复制配置文件到目录
	从源文件目录下复制
	redis.conf
	* cp ./redis.conf /usr/local/redis/conf/

6,配置为服务,复制配置文件
	从源目录下复制文件到 /etc/ini.d 目录下,更名为redis
	cp ./utils/redis_init_script /etc/init.d/redis

7,编辑配置
	vim /etc/init.d/redis

------------------------
5.x版本的安装			|
------------------------
	# 安装必须依赖
		yum install -y gcc

	# 准备目录
		mkdir /usr/local/redis
		mkdir /usr/local/redis/bin
		mkdir /usr/local/redis/conf

	# 下载源码,解压
		https://redis.io/

	# 进入目录执行
		make

	# 复制程序文件到目录
		cd ./redis-5.0.3/src

		cp ./mkreleasehdr.sh /usr/local/redis/bin
		cp ./redis-benchmark /usr/local/redis/bin
		cp ./redis-check-aof /usr/local/redis/bin
		cp ./redis-check-rdb /usr/local/redis/bin
		cp ./redis-cli /usr/local/redis/bin
		cp ./redis-sentinel /usr/local/redis/bin
		cp ./redis-server /usr/local/redis/bin
		cp ./redis-trib.rb /usr/local/redis/bin
	
	# 复制配置文件到目录
		cp ./redis-5.0.3/redis.conf /usr/local/redis/conf

	
