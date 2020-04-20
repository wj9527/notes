---------------------------------
Gson
---------------------------------
	# Maven
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-json</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
		</dependency>
	
	# 注解驱动, 不加载默认的Jackson
		@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })
	
	# 配置(配置类: GsonProperties)
		spring.http.converters.preferred-json-mapper=gson
		spring.gson.date-format=
		spring.gson.disable-html-escaping=
		spring.gson.disable-inner-class-serialization=
		spring.gson.enable-complex-map-key-serialization=
		spring.gson.exclude-fields-without-expose-annotation=
		spring.gson.field-naming-policy=
		spring.gson.generate-non-executable-json=
		spring.gson.lenient=
		spring.gson.long-serialization-policy=
		spring.gson.pretty-printing=
		spring.gson.serialize-nulls=
		

		* 参考文档
			https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#json-properties

	# SpringBoot 通过 GsonAutoConfiguration 类来自动的装载 Gson
		* 可以在组件中注入
			@Autowired
			Gson gson;
