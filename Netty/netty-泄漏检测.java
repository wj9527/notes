-------------------------------
泄漏检测						|
-------------------------------
	# Netty 定义了四种泄漏检测等级
		Disables
		SIMPLE
		ADVANCED
		PARANOID

		* 修改检测等级，只需修改 io.netty.leakDetectionLevel 系统属性
			java -Dio.netty.leakDetectionLevel=paranoid