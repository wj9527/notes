-----------------------------
Elasticsearch			 	 |
-----------------------------
	# 官网
		https://www.elastic.co
		https://www.elastic.co/cn/
		https://www.elastic.co/guide/en/elasticsearch/reference/index.html

	# Github
		https://github.com/elastic/elasticsearch
	
	# 参考
		https://blog.csdn.net/laoyang360/article/details/79293493

	

-----------------------------
Elasticsearch-目录结构		 |
-----------------------------
	bin
		|-x-pack
			|-certgen.bat
			|-.bat
			|-certutil.bat
			|-migrate.bat
			|-saml-metadata.bat
			|-setup-passwords.bat
			|-sql-cli.bat
			|-syskeygen.bat
			|-users.bat
		|-elasticsearch-sql-cli-6.3.0.jar
		|-elasticsearch.bat
		|-elasticsearch-certgen.bat
		|-elasticsearch-certutil.bat
		|-elasticsearch-croneval.bat
		|-elasticsearch-env.bat
		|-elasticsearch-keystore.bat
		|-elasticsearch-migrate.bat
		|-elasticsearch-plugin.bat
		|-elasticsearch-saml-metadata.bat
		|-elasticsearch-service.bat
		|-elasticsearch-setup-passwords.bat
		|-elasticsearch-sql-cli.bat
		|-elasticsearch-syskeygen.bat
		|-elasticsearch-translog.bat
		|-elasticsearch-users.bat
		|-x-pack-env.bat
		|-x-pack-security-env.bat
		|-x-pack-watcher-env.bat
	config
		|-elasticsearch.keystore
		|-elasticsearch.yml
		|-jvm.options
		|-log4j2.properties
		|-role_mapping.yml
		|-roles.yml
		|-users
		|-users_roles
	data
	lib
	logs
	modules
	plugins

-----------------------------
Elasticsearch-启动JSON		 |
-----------------------------
	# http://127.0.0.1:9200/

	{
		"name" : "b9yxBCU",								//节点名称
		"cluster_name" : "elasticsearch",				//集群名称
		"cluster_uuid" : "V3_diOPVS7m85S_dmijzfw",		//集群uuid
		"version" : {
			"number" : "6.3.0",							//版本号
			"build_flavor" : "default",				
			"build_type" : "zip",		
			"build_hash" : "424e937"
			"build_date" : "2018-06-11T23:38:03.357887Z",
			"build_snapshot" : false,
			"lucene_version" : "7.3.1",
			"minimum_wire_compatibility_version" : "5.6.0",
			"minimum_index_compatibility_version" : "5.0.0"
		},
		"tagline" : "You Know, for Search"
	}

-----------------------------
Elasticsearch-核心概念		 |
-----------------------------
	Near Realtime(NRT)
		# 近实时,两个意思,从写入数据到可以搜索大约会有1s的延迟,基于es进行搜索和分析可以达到秒级
	
	Cluster
		# 节点,集群中的一个节点,节点会有一个名称,默认是随机分配的
		# 节点名称很重要,节点默认会去加入一个名为:elaticsearch 的集群
		# 如果启动一堆节点,那么它们会自动组成一个es集群,当然,一个节点也可以组成一个es集群
	
	Index
		# 索引,包含一堆有相似结构的文档数据,比如可以有一个客户索引,商品分类索引,订单索引,索引是有一个名称的
	
	Type
		# 类型,每个索引里都可以有一个或者多个type,type是index中的另一个逻辑分类
		# 一个type下的document都有相同的field,比如博客系统,有一个索引,可以定义用户数据type,博客数据type,评论数据type
	
	Documen
		# 文档,es种的最小数据单元,一个document可以是一条客户数据,一条商品分类数据
		# 通常使用JSON数据结构表示,每个index下的type中,都可以存储多个document
	
	Shard
		# 单台机器无法存储大量数据,es可以吧一个索引数据分为多个shard,分布式在多台服务器上存储
		# 有了Shard就可以横向扩展,存储更多数据,让搜索和分析等操作分布到多台服务器上去执行,提升吞吐量和性能
		# 每个shard都是一个lucene index
	
	Replica
		# 任何一个服务器都有可能会宕机,此时shard就会丢失,因此可以为每个shard创建n个replica副本
		# replia可以在shard故障时,提供服务,保证shard的不丢失,多个replica还可以提升搜索操作的吞吐量和性能
		# Shard   -> primary shard(建立索引时一次设置,不能修改,默认5个)
		# Replica -> replica shard(随时修改数量,默认1个)
		# 默认每个索引有10个shard,5个primary shard,5个replica shard
		# 最小的高可用peizhi,是两台服务器
	

	# 传统关系型数据库的对比
		Relational DB ->	Databases	-> Tables	-> Rows			-> Columns
		Elasticsearch ->	Indices		-> Types	-> Documents	-> Fields



-----------------------------
Elasticsearch-征途			 |
-----------------------------
倒排索引详解
