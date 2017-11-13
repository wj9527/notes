---------------
Tomcat			|
---------------
	# Centos7下,Tomcat8.5.x启动卡住解决方案
	# 修改 $JAVA_HOME/jre/lib/security/java.security 文件的securerandom.source配置
		* securerandom.source=file:/dev/./urandom
		* 第117行
