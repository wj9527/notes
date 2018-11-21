--------------------
配置文件详解		|
--------------------
	* redis.conf
	* 复制到Server目录	
	
	
	daemonize no
		* Redis服务是否后台启动,默认值为:no也就是后台启动.
		* 修改为:yes 那么Redis会以后台启动的方式启动服务

	port 6379
		* 指定Redis监听的端口.默认为:6379

	pidfile /var/run/redis.pid
		* 当Redis在后台的运行的时候,默认会把pid文件放在:/var/run/redis.pid
		* 可以配置为其他的地址,当运行多个redis服务的时候,需要指定不同管道pid,文件,和端口

	notify-keyspace-events
		* 配置事件监听
		* notify-keyspace-events="Ex"  # 监听key的过期事件
			K	键空间通知，所有通知以 以__keyspace@<db>__为前缀 ，针对Key
			E	键事件通知，所有通知以 以__keysevent@<db>__为前缀，针对event
			g	DEL 、 EXPIRE 、 RENAME 等类型无关的通用命令的通知
			$	字符串命令的通知
			l	列表命令的通知
			s	集合命令的通知
			h	哈希命令的通知
			z	有序集合命令的通知
			x	过期事:每当有过期键被删除时发送
			e	驱逐(evict)事件：每当有键因为 maxmemory 政策而被删除时发送
			A	参数 g$lshzxe 的别名，相当于是All
	