------------------------
HikariCP				|
------------------------
	# 一个比Druid更快的连接池
	# Github
		https://github.com/brettwooldridge/HikariCP
	# SpringBoot2默认使用(不需要额外的starter)
	# Maven导入
		<dependency>
			<groupId>com.zaxxer</groupId>
			<artifactId>HikariCP</artifactId>
		</dependency>
	
	# 配置项

spring:
  # 基本配置
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/mall?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowMultiQueries=true
    username: root
    password: root
  
    # 连接池的配置属性
    hikari:
	  catalog: 
	  connection-timeout: 
	  validation-timeout: 
	  idle-timeout: 
	  leak-detection-threshold: 
	  max-lifetime: 
	  max-pool-size: 
	  min-idle: 
	  username: 
	  password: 
	  initialization-fail-timeout: 
	  connection-init-sql: 
	  connection-test-query: SELECT 1
	  data-source-class-name: 
	  data-source-jndi-name: 
	  driver-class-name: 
	  jdbc-url: 
	  pool-name: 
	  schema: 
	  transaction-isolation-name: 
	  is-auto-commit: 
	  is-read-only: 
	  is-isolate-internal-queries: 
	  is-register-mbeans: 
	  is-allow-pool-suspension: 
	  data-source: 
	  data-source-properties: 
	  thread-factory: 
  	  scheduled-executor: 
	  metrics-tracker-factory: 
  	  metric-registry: 
	  health-check-registry: 
      health-check-properties: 
	  sealed: 
	
	# 代码
		@Bean
		@ConfigurationProperties(prefix = "spring.datasource")
		public DataSource dataSource(){
			HikariDataSource hikariDataSource = new HikariDataSource();
			return hikariDataSource;
		}

------------------------
编码配置				|
------------------------
@Bean
public DataSource dataSource() {
	HikariConfig config = new HikariConfig();
	config.setJdbcUrl(dataSourceUrl); //数据源
	config.setUsername(user); //用户名
	config.setPassword(password); //密码
	config.addDataSourceProperty("cachePrepStmts", "true"); //是否自定义配置，为true时下面两个参数才生效
	config.addDataSourceProperty("prepStmtCacheSize", "250"); //连接池大小默认25，官方推荐250-500
	config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //单条语句最大长度默认256，官方推荐2048
	config.addDataSourceProperty("useServerPrepStmts", "true"); //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
	config.addDataSourceProperty("useLocalSessionState", "true");
	config.addDataSourceProperty("useLocalTransactionState", "true");
	config.addDataSourceProperty("rewriteBatchedStatements", "true");
	config.addDataSourceProperty("cacheResultSetMetadata", "true");
	config.addDataSourceProperty("cacheServerConfiguration", "true");
	config.addDataSourceProperty("elideSetAutoCommits", "true");
	config.addDataSourceProperty("maintainTimeStats", "false");

	HikariDataSource ds = new HikariDataSource(config);
	return ds;
}