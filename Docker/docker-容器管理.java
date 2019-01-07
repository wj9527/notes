----------------------------
容器管理					|
----------------------------
	# 启动容器
		docker run [image] [cmd]
			-d
				* 以守护进程的形式启动容器
			-p
				* 端口映射
				* -p [宿主机端口]:[容器端口]
			-it
				* 以交互形式启动容器
			
			--name
				* 自定义名称(多个运行的容器名称不能重复)
				*  --name [name]
	
			image
				* 镜像

			cmd
				* 向容器执行的命令


	# 查看运行的容器
		docker -ps
			-a
				* 包含未运行的容器
			
			------------------------------------------------------------------------------------------------------------------------------------------------------
			CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
			b860f33a7e02        nginx               "nginx -g 'daemon of…"   8 minutes ago       Up 8 minutes        80/tcp              keen_galois
			------------------------------------------------------------------------------------------------------------------------------------------------------

	
	# 优雅停止容器
		docker stop [id]
			id
				* CONTAINER ID,或者是自己定义的名称


	# 强制杀死容器
		docker kill [id]
			id
				* CONTAINER ID,或者是自己定义的名称
		

	# 复制文件到容器
		docker cp [file] [id]:[path]
			* 把file复制到名字为id的path路径下
			* id也可以是容器的CONTAINER ID
	
	#  查看容器的日志
		docker logs -f bf08b7f2cd89
		