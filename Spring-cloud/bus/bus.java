---------------------
bus					 |
---------------------
	# 文档
		https://cloud.spring.io/spring-cloud-bus/spring-cloud-bus.html
	
	# 需要依赖到MQ中间件
		RabbitMQ
		Kafka
	
	# 模型
		* 客户端添加,bus依赖,连接到 mq
		* 配置内容被修改后,触发任意客户端的 /actuator/bus-refresh
		* 此时,所有连接到bus的客户端都会发生更新操作

	# 客户端 Maven,bus的实现,二选一
		dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
			* amqp就是Rabbitmq
		
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-kafka</artifactId>
        </dependency>
			* 使用Kafka
		
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
	
		* actuator 模块的作用是提供 /actuator/bus-refresh 端点,用于触发任意客户端的刷新,从而通知到所有连接到Bus的客户端
		* 一般而言,负责执行刷新操作的客户端一般都是 : config-server
		* 其他的微服务客户端不必承担刷新配置的工作,从而简化了集群的一些维护工作
		* 其他的微服务客户端也许并不需要 actuator 依赖

	# RabbitMQ客户端配置
spring:
  rabbitmq:
    host: 58.87.75.64
    port: 5671
    username: guest
    password: guest
    ssl:
      enabled: true


	# Kafka客户端配置
		TODO

	# 负责触发刷新事件的节点,需要配置 actuator,提供 /bus-refresh 端点
management:
  endpoints:
    web:
      base-path: /actuator
	  # 需要开放  /bus-refresh  端点
      exposure:
        include:
          - '*'