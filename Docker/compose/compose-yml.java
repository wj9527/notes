
# 配置文件参考
	https://github.com/docker/docker.github.io/blob/master/compose/compose-file/index.md


# 常用

version: "[版本号]"
service:
  [应用名称]:
    image:
    build: [指定Dockerfile目录,会新构建一个镜像]
	ports:
	  - "[宿主机端口]:[容器端口]"
    volumes:
	  - [宿主机目录]:[容器目录]
    networks:
	  - [网络]
	deploy:
	# 部署选项
	  replicas: [集群数量]
	  update_config:
	    parallelism: 2
		delay: 10s
		restart_policy:
		# 重启策略
		  condition: on-failure
		  # 重启条件,枚举值