
-------------------------
ApplicationPidFileWriter |
-------------------------
	# 在sb应用启动后, 用于把进程id写入到文件的监听器

	# 配置监听器
		SpringApplication springApplication = new SpringApplication(MyApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
	
	# 属性配置
		spring.pid.file=D://app.pid
			* 写入pid的文件

		spring.pid.fail-on-write-error=D://app.error
			* 如果pid文件写入失败, 则会把异常信息写入到这个文件
		
	