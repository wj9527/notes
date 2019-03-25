--------------------------------
Winddows 安装MySQL				|
--------------------------------
	# 下载windows压缩包,解压
		https://dev.mysql.com/downloads/mysql/

		* 可以点击: Looking for previous GA versions? 来下载以前的版本(5.7.x)
	
	# 添加环境变量
		MYSQL_HOME=D:\mysql-5.7.20-winx64
	
	# 添加PATH变量
		%MYSQL_HOME%\bin;

	# 创建ini配置文件
		* 在mysql的解压根目录创建文件:my.ini
[mysqld]
basedir=D:\mysql-5.7.20-winx64
datadir=D:\mysql-5.7.20-winx64\data 
port=3306
max_connections=200
character_set_server=utf8
default_storage_engine=INNODB
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES

	
	# 安装到系统服务
		mysqld --install

		* 使用系统命令维护
			net start mysql
			net stop mysql
		
		* 出错可以卸载重新安装
			mysqld remove
	
	# 初始化
		mysqld --initialize-insecure

		* 初始化无密码的root用户
		* 登录时输入密码直接回车
		* 登录成功后修改密码
			set password for 'root'@'%' = password('root');





