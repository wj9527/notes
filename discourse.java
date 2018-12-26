---------------------
discourse			 |
---------------------
	# github
		https://github.com/discourse/discourse

	# 安装
		https://github.com/discourse/discourse_docker
		https://github.com/discourse/discourse/blob/master/docs/INSTALL-cloud.md
	

	# Docker安装
		* 移除旧版本
yum remove docker \
docker-client \
docker-client-latest \
docker-common \
docker-latest \
docker-latest-logrotate \
docker-logrotate \
docker-selinux \
docker-engine-selinux \
docker-engine
		* 安装系统所需要的工具
			yum install -y yum-utils device-mapper-persistent-data lvm2
		
		* 添加软件源信息
			yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
		
		* 更新 yum 缓存
			yum makecache fast
		
		* 安装 Docker-ce
			yum -y install docker-ce
		
		* 启动 Docker 后台服务
			systemctl start docker
		
		* 测试运行 hello-world
			docker run hello-world
		 
		* 删除docket ce
			yum remove docker-ce
			rm -rf /var/lib/docker
	
	# 

---------------------
launcher 维护		 |
---------------------
	语法: launcher COMMAND CONFIG [--skip-prereqs] [--docker-args STRING]
	COMMAND:
		start:      Start/initialize a container
			* 初始化一个container
		stop:       Stop a running container
			* 停止一个container
		restart:    Restart a container
			* 重启一个container
		destroy:    Stop and remove a container
		enter:      Use nsenter to get a shell into a container
		logs:       View the Docker logs for a container
		bootstrap:  Bootstrap a container for the config based on a template
		rebuild:    Rebuild a container (destroy old, bootstrap, start new)
			* 重新构建
		cleanup:    Remove all containers that have stopped for > 24 hours

	Options:
		--skip-prereqs             Don't check launcher prerequisites
		--docker-args              Extra arguments to pass when running docker