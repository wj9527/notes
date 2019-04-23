----------------------------
config						|
----------------------------
	# Maven
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-client</artifactId>
        </dependency>
	
	# ¼Ü¹¹
						 <--------->	Service1
						|
		GIT <----> ConfigServer			
						|
						 <--------->	Service2
	

