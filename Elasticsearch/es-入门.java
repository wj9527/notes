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

	
	# 传统关系型数据库的对比
		Relational DB ->	Databases	-> Tables	-> Rows			-> Columns
		Elasticsearch ->	Indices		-> Types	-> Documents	-> Fields


-----------------------------
Elasticsearch-目录结构		 |
-----------------------------
	bin
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