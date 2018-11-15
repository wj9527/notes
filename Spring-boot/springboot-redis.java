------------------------------------
Redis-整合单机版					|
------------------------------------
	# 使用lettuce
	# 依赖
		<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-data-redis</artifactId>
    	</dependency>
		<dependency>
		    <groupId>io.lettuce</groupId>
		    <artifactId>lettuce-core</artifactId>
		</dependency>
		<dependency>
		    <groupId>org.apache.commons</groupId>
		    <artifactId>commons-pool2</artifactId>
		</dependency>
	
	# 配置文件
		# Redis数据库索引（默认为0）
		spring.redis.database=0  
		# Redis服务器地址
		spring.redis.host=192.168.0.58
		# Redis服务器连接端口
		spring.redis.port=6379  
		# Redis服务器连接密码(默认为空)
		spring.redis.password=  
		# 连接池最大连接数(使用负值表示没有限制)
		spring.redis.lettuce.pool.max-active=8  
		# 连接池最大阻塞等待时间(使用负值表示没有限制)
		spring.redis.lettuce.pool.max-wait=-1  
		# 连接池中的最大空闲连接
		spring.redis.lettuce.pool.max-idle=8  
		# 连接池中的最小空闲连接
		spring.redis.lettuce.pool.min-idle=0  
		# 连接超时时间(毫秒)
		spring.redis.timeout=2000

	# 使用
		* StringRedisTemplate
			* 是RedisTemplate的子类
			* 一般大多数都是使用它
		* 针对jedis客户端中大量api进行了归类封装,将同一类型操作封装为operation接口
			ValueOperations	简单K-V操作
			SetOperations	set类型数据操作
			ZSetOperations	zset类型数据操作
			HashOperations	针对map类型的数据操作
			ListOperations	针对list类型的数据操作
		* demo
			@Autowired
			private StringRedisTemplate stringRedisTemplate;

			stringRedisTemplate.opsForValue();		//获取操作简单k-v的api
			stringRedisTemplate.opsForSet();		//获取操作set的api
	
		
			
------------------------------------
Redis-整合集群						|
------------------------------------	
	
	

------------------------------------
Redis- scan 代替 keys *				|
------------------------------------
	public void scan(String pattern, Consumer<byte[]> consumer) {
		this.stringRedisTemplate.execute((RedisConnection connection) -> {
			try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(Long.MAX_VALUE).match(pattern).build())) {
				cursor.forEachRemaining(consumer);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
	}