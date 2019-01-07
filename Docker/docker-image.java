-------------------------
image					 |
-------------------------
	# 查看本地的镜像(列出镜像)
		docker images
		--------------------------------------------------------------------------------------
		REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
		hello-world           latest              fce289e99eb9        6 days ago          1.84kB
		nginx                 latest              7042885a156a        9 days ago          109MB
		--------------------------------------------------------------------------------------

	# 镜像拉取
		docker pull [name]:[tag]
	
	# 删除镜像
		docker rmi [name]
			* 仅仅只会删除 last 版本
			* 也可以把name换成指定的image id

	
	# 导出镜像
		docker save [name]:[tag] > /[path].image
		
	# 导入镜像
		docker load < /[path].image
	
	