--------------------------------
quartz	整合					|
--------------------------------
	# 导入依赖
		<dependency>
		   <groupId>org.springframework.boot</groupId>
		   <artifactId>spring-boot-starter-quartz</artifactId>
		</dependency>
	
	# 配置:QuartzProperties(配置类)
spring:
  quartz:
    # 枚举:MEMORY(默认),JDBC
    job-store-type:
    scheduler-name:
    auto-startup:
    startup-delay:
    wait-for-jobs-to-complete-on-shutdown: false
    overwrite-existing-jobs: false
    properties:
    jdbc:
      schema: classpath:org/quartz/impl/jdbcjobstore/tables_@@platform@@.sql
      # 枚举:EMBEDDED(默认),ALWAYS,NEVER
      initialize-schema: 
      comment-prefix: --


	# 任务类继承:QuartzJobBean 
