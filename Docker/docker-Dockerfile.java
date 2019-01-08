------------------------
Dockerfile				|
------------------------
	# Dockerfile基于DSL(Domain Specifile Language)语法的指令来构建一个Docker镜像
	# Docker大体上按照如下流程执行Dockerfile中的命令
		* Docker 从基础镜像运行一个人容器
		* 执行一条指令,对容器做出修改
		* 执行类似于 Docker Commit 的操作,提交一个新的镜像层
		* Docker再基于刚才提交的镜像运行一个新容器
		* 执行Dockerfile中的下一条指令直到所有指令都执行完毕

	# 如果Dockerfile由于一些原因没有正常结束,还是可以得到一个可以使用的镜像
		* 可以基于该镜像生成一个容器,进入交互式环境查看排查原因

	# 以 # 开头表示注释
	# 第一条之类必须是: FROM
		* 表示基础镜像,它必须指定一个已经存在的镜像
	

------------------------
Dockerfile				|
------------------------
	FROM
		* 基本的镜像
			FROM centos:7

	MAINTAINER
		* 作者信息
			MAINTAINER KevinBlandy "747692844@qq.com"
	ENV
		* 环境变量
			ENV JAVA_HOME /usr/local/java
		* 也可以一次性设置多个
			ENV JAVA_HOM=/home/java PYTHON_HOME=/home/python
		* 也可以在其他的指令中引用该变量
			ENV DIR /opt/app
			WORKDIR $DIR
		* 这些环节变量会持久保存到镜像创建的容器,可以用过 env 指令查看

	RUN 
		* 构建时候,执行的shell命令
			RUN yum -y install git

	EXPOSE
		* 对外公开的端口
			EXPOSE 80
	
	CMD
		* 容器启动时运行的命令
			COM ["/bin/true","-l"]
		* 跟 docker run 指令后指定的命名一样的,而且该指令指定的命名会覆盖它
		* 整个文件中只能定义一个CMD指令,如果定义了多个,那么只有最后一个生效
	
	ENTRYPOINT
		* 类似于 CMD 指令,也是用来执行命令的
			ENTRYPOINT ["/usr/sbin/nginx"]
		* 它不会被 docker run 指令的命令覆盖
		* docker run 指令的的命令参数会作为值,传递给这个命令
			ENTRYPOINT ["/usr/sbin/nginx"]
				+
			docker run ... nginx-image -g "daemon off"
				=
			/usr/sbin/nginx -g "daemon off"
		
		* 实在需要覆盖该命令,可以通过 --entrypoint 来覆盖
		* 可以配合CMD使用生成一条指令
			ENTRYPOINT ["/usr/sbin/nginx"]
				+
			CMD ["-h"]
				=
			/usr/sbin/nginx -h
		

	WORKDIR
		* 在从镜像创建一个新的容器时,会在容器内部内置一个工作目录
			WORKDIR /usr/local/webapp
		* CMD 和 ENTRYPOINT 指令都会在这个目录下执行
		* 该命令可以出现多次,表示目录切换
			WORKDIR /usr/local/db
			RUN mysql install
			WORKDIR /usr/local/webapp
			ENTRYPOINT ["setup"]
		*  可以在docker run时通过 -w 参数来覆盖该配置
			docer run ... -w /var/log ...
		

	USER
		* 指镜像会以哪个用户去执行
			USER root
		* 可以指定用户名,uid,用户组,gid,以及各种组合
			USER user
			USER user:group
		
		* 如果未指定,默认为 root
	
	VOLUME
		* 基于镜像创建的容器,添加卷
		* 一个卷可以存在一个或者多个容器内特定的目录,这个目录可以绕过联合文件系统,并且提供如下共享数据或者对数据进行持久化的功能
			* 卷可以在容器之间共享和重用
			* 对卷的修改是立即生效的
			* 对卷的修改,不会对更新镜像产生影响
			* 卷会一直存在,直到没有容器使用它
		* 可以一致性指定一个或者多个卷
			VOLUME ["/opt/project","/data"]
		
	ADD
		* 把构建环境下的文件和目录复制到镜像中
		* 需要指定源文件和目标文件的位置
			ADD software.lic /opt/application/software.lic
		* 源文件也可以是一个url,但是不能对构建目录外的文件进行操作
		* 通过结尾来判断是文件还是文件夹,如果结尾以 / 字符结尾,表示为一个文件夹,反之则为一个文件
		* 如果源文件是压缩文件(zip),而目标是一个目录,则会主动解压
			ADD a.zip /usrl/local/a/
		
		* 该指令会让构建缓存失效,如果通过ADD指令向镜添加一个文件或者目录,那么Df文件中的后续指令不能继续使用之前的缓存
	COPY
		* 跟ADD一样,唯一不同的时候,如果cp的时候压缩文件,不会进行解压操作


	LABEL
		*  为Docker镜像添加元数据信息,k=v形式展示
			LABEL version="10.1"
			LABEL location="中国 - 重庆" type="DLK" role="ADMIN"
		* 可以通过 docker inspect 来查看 Docker 镜像中的标签信息
	
	STOPSIGNAL
		* 停止容器的时候,发送指定的系统信号给容器
		* 信号必须是内核系统调用中的合法数(数字或者名称)
			STOPSIGNAL 9 
			STOPSIGNAL SIGKILL
		
	ARG
		* 可以在docker build命令运行时候传递构建运行时的变量
		* 在构建时使用 --build-arg,用户只能在构建时指定Df文件中定义过的参数
			ARG build
			docker build ... --build-arg build=1234 ....

		* 也可以有默认值,如果build时没指定,则使用默认值
			ARG webapp_usr=user
		
		* Docker预定义了N多ARG变量,可以直接赋值使用
			HTTP_PROXY
	
	ONBUILD
		* 为镜像添加触发器,当该镜像被其他镜像当作基础镜像的时候,该镜像中的触发器会被执行
		* 触发器会在构建过程中插入新指令,这些指令是跟在FROM之后执行的(FROM 哪个镜像,哪个镜像的触发器就会执行)
		* 触发器可以是任何构建之类
			ONBUILD ADD . /app/src/
			ONBUILD RUN cd /app/src && make && make install
		* 触发器会按照父级镜像中的指定顺序执行,并且只能被继承一次(只能在子镜像中运行,不能在孙子镜像中运行)

		
		
					
		
		
			


	


	
