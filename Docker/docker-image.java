-------------------------
image					 |
-------------------------
	# 查询镜像
		docker search [name]
			name
				* 查询指定的镜像
		————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		NAME                                   DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
		mysql                                  MySQL is a widely used, open-source relation…   7649                [OK]                [ok]
		————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		NAME 名称
		DESCRIPTION 描述
		STARS 星星数量(受到欢迎的程度)
		OFFICIAL 是否是官方管理的
		AUTOMATED 是否DockerHub自动构建的


	# 查看本地的镜像(列出镜像)
		docker images [name]
			name
				* 仅仅列出本地的镜像
			-a
				* 显示所有的镜像

			--digest
				* 显示摘要信息
			
			--no-trunc
				* 显示完整信息
			
		--------------------------------------------------------------------------------------
		REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
		hello-world           latest              fce289e99eb9        6 days ago          1.84kB
		nginx                 latest              7042885a156a        9 days ago          109MB
		--------------------------------------------------------------------------------------
	
	# 镜像拉取
		docker pull [name]:[tag]
			name
				* 镜像名称
			tag
				* 标签(版本号)
			-a
				* 下载指定镜像的所有tag(版本)到本地
	
	# 删除镜像
		docker rmi [name]
			* 仅仅只会删除 last 版本
			* 也可以把name换成指定的image id
			* 删除所有的镜像
				docker rmi `docker images -q`

	
	# 导出镜像
		docker save [name]:[tag] > /[path].image
		
	# 导入镜像
		docker load < /[path].image
	
	