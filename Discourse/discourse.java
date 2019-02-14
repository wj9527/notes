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


---------------------
杂七杂八			 |
---------------------
	# 阿里云部署时配置邮件要注意

		* 添加如下配置

DISCOURSE_SMTP_AUTHENTICATION: login
DISCOURSE_SMTP_OPENSSL_VERIFY_MODE: none
DISCOURSE_SMTP_ENABLE_START_TLS: true

		* 此外在配置文件最后的 run: 那一块中找到
			run:
			  - exec: echo "Beginning of custom commands"

			  ## If you want to set the 'From' email address for your first registration, uncomment and change:
			  #- exec: rails r "SiteSetting.notification_email='info@unconfigured.discourse.org'"
			  ## After getting the first signup email, re-comment the line. It only needs to run once. 

		* 删除掉 #- exec: rails r "SiteSetting.notification_email='info@unconfigured.discourse.org'"* 这一行开头的 # 井号
		* 再把 info@unconfigured.discourse.org 改成你的发件邮箱地址
		* 编辑文件的时候不要删除每一行前的空格符,保持语句块上下是对齐的,不要删除没说明的引号

		Kev1nB!andy(ecs)=>ecs.run();
		Kev1nB!andy(happy)=> return happy*2;
		Kev1nB!andy(name)=>println(name);


---------------------
杂七杂八			 |
---------------------

	# 设置顶部全局提示信息
		设置 -> 未分类 > global notice

	# 开启帖子标签功能
		设置 -> 标签 
			min trust to create tag			创建标签所需的最小信任等级
			min trust level to tag topics	给主题加标签的最小信任等级
	
	# 设置分类页面的样式(/categories)
		设置 -> 基本设置 -> desktop category page style
	
	# 设置主页的菜单(帖子布局)
		设置 -> 基本设置 -> post menu
	
	# 帖子审核机制
		设置 -> 发帖 -> approve post count
		设置 -> 发帖 -> approve unless trust level
		设置 -> 发帖 -> approve new topics unless trust level
	
	# 询问是否允许通知
		设置 -> 基本设置 -> push notifications prompt

	# 设置通知图标
		设置 -> 基本设置 -> push notifications icon

	# Github登录
		设置 -> 登录 -> github

		* github的回调地址是
			{site}/auth/github/callback
	
	# 自定义html头部信息
		* 设置统计代码
		* 设置自己的代码登录
	
	# 自动备份
		设置 -> 备份 -> backup frequency
			* 多少天备份一次
		
		设置 -> 备份 -> allow restore
			* 允许导入备份的数据,这会替换全站的数据
		