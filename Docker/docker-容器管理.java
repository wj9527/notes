----------------------------
容器管理					|
----------------------------
	# 容器所在的本地目录
		/var/lib/docker/containers
	

----------------------------
容器管理					|
----------------------------
	# 新启动容器
		docker run [image] [cmd]
			-d
				* 以守护进程的形式启动容器
			-p
				* 端口映射
					-p [宿主机端口]:[容器端口]

				* 如果一次性启动多个容器,那么只会有一个与宿主机绑定成功

				* 如果存在多块网卡(多个ip),也可以指定
					-p 127.0.0.1:8081:80

				* 如果只有一个参数,则表示容器的端口,而会随机选择一个宿主机的端口进行绑定(建议这种方式同时启动多个容器)
					-p 80

				* 可以在后面添加 /udp 表示映射的是udp端口
					 -p 1024:1024/udp

			-P
				* 对外公开 Dockerfile 中 EXPOSE 定义的所有端口
				* 每个对外公开的端口,都会随机选择一个宿主机端口进行绑定

			-it
				* 以交互形式启动容器
				* -i 开启了容器的stdin
				* -t 开启一个tty的伪终端
			-e
				* 指定环境变量
					-e "WEB_PORT=8080"
			-v
				* 设置共享卷 -v [宿主机目录]:[虚拟机目录]
					-v /usr/local/website:/var/www/html/website
				* 如果目录不存在,docker会自动的创建
				* 还可以添加权限来控制是否只读
					-v /usr/local/website:/var/www/html/website:ro

			--name
				* 自定义名称(多个运行的容器名称不能重复)
				*  --name [name]
				* '在docker很多命令中,都可以通过该名称来操作容器'

			--restart
				* 指定容器的重启策略
					--restart=always
				* 枚举值
					always
						只要容器被关闭就会立即重启,不论任何原因导致的关闭
					on-failure
						只有容器的退出代码非0时才会重启(非正常关闭,则重启)
						它还接收一个最大重启次数: --restart=on-failure:5
					
			--entrypoint
				* 覆写的是Dockerfile里面的 ENTRYPOINT 指令
				* 会把 [cmd] 当作参数传递给该命令执行
			
			--net
				* 指定容器运行的网络
					--net=mynet
				* 该网络需要先创建

			image
				* 镜像

			cmd
				* 启动OK后,向容器执行的命令,覆写的是Dockerflile里面的 CMD 指令
				* 如果包含特殊符号,需要用""包含

	
	# 重新启动已经停止的容器
		docker start [id]
			id
				* 容器id或者name
			

	# 查看运行的容器
		docker -ps
			-a
				* 包含未运行的容器
			-l
				* 仅仅列出最后一次运行的容器
			-n
				* 指定显示的数量
				* -n 15
			-q
				* 仅仅查看(返回)容器的id信息
			
			------------------------------------------------------------------------------------------------------------------------------------------------------
			CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
			b860f33a7e02        nginx               "nginx -g 'daemon of…"   8 minutes ago       Up 8 minutes        80/tcp              keen_galois
			------------------------------------------------------------------------------------------------------------------------------------------------------

			* STATUS 后面的括号有一个状态值,如果该值为0,则表示是正常退出的
			* NAMES 随机的名称(贼有意思)

	
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
		docker logs [id]
			id
				* 容器的id或者name
			-f
				* 可以即时的看到日志刷新
			-t
				* 为每条日志加上时间信息
	

	# 删除容器
		docker rm [id]
			id
				* 容器的hash或者name
			-f
				* 可以删除运行时的容器
				* 如果不添加该参数,则只能删除停止状态的容器

			* 删除所有容器
				docker rm `docker ps -a -q`
	
	# 查看容器内的进程
		docker top [id]
			id
				* 容器的id或者name
	
	# 查看容器的状态
		docker stats [id]
			id
				* 容器的id或者name
	
	# 在容器内部运行进程
		docker exec [id] [cmd]
			id
				* 容器id或者name
			cmd
				* 启动命令
				* 如果包含特殊符号,需要用""包含
			-it
				* 在交互模式下进行
			
	
	
	# 查看容器的详细信息(返回json信息)
		docker inspect [id]
			id
				* 容器id或者name
				* 在这里可以指定一个或者多个,用空格隔开
			-f
				* 格式化(仅仅查看指定的json信息,json导航)
					docker inspect -f "{{.NetworkSettings.SandboxID}}" name 

				* 格式化的参数,需要用""包裹,因为包含了特殊的字符
				* -f 完全支持go语言的模板(不会,滚)
				* 该参数也可以同时指定多个参数,表示查看多个信息值
					docker inspect -f "{{.NetworkSettings.SandboxID}} {{.name}}" name
				* 其实参数就是一个字符串模板,然后可以用go的表达式去配置信息中取值来填充
	
	
	# 查看(返回)容器映射(与宿主机)的端口信息
		docker port [id] [port]
			id
				* 容器name或者id
			port
				* 容器的开放的端口
	
			
		
	
			
		
	

			
			


	
		