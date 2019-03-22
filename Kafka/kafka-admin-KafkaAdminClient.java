-------------------------
KafkaAdminClient		 |
-------------------------
	# 继承自:AdminClient
	# 实例的创建
		KafkaAdminClient KafkaAdminClient.create(Map<String,Object> config);
		KafkaAdminClient KafkaAdminClient.create(Properties properties)
	
	# 方法
		AlterConfigsResult alterConfigs(Map<ConfigResource, Config> configs, final AlterConfigsOptions options)
		AlterReplicaLogDirsResult alterReplicaLogDirs(Map<TopicPartitionReplica, String> replicaAssignment, final AlterReplicaLogDirsOptions options)

		void close(long duration, TimeUnit unit);

		CreateAclsResult createAcls(Collection<AclBinding> acls, CreateAclsOptions options)
		CreateDelegationTokenResult createDelegationToken(final CreateDelegationTokenOptions options)
		CreatePartitionsResult createPartitions(Map<String, NewPartitions> newPartitions,final CreatePartitionsOptions options)
		CreateTopicsResult createTopics(final Collection<NewTopic> newTopics,final CreateTopicsOptions options)
			* 创建topic

		DeleteAclsResult deleteAcls(Collection<AclBindingFilter> filters, DeleteAclsOptions options)
		DeleteConsumerGroupsResult deleteConsumerGroups(Collection<String> groupIds, DeleteConsumerGroupsOptions options)
		DeleteRecordsResult deleteRecords(final Map<TopicPartition, RecordsToDelete> recordsToDelete, final DeleteRecordsOptions options)
		DeleteTopicsResult deleteTopics(Collection<String> topicNames, DeleteTopicsOptions options)
		DescribeAclsResult describeAcls(final AclBindingFilter filter, DescribeAclsOptions options)
		DescribeClusterResult describeCluster(DescribeClusterOptions options) 
		DescribeConfigsResult describeConfigs(Collection<ConfigResource> configResources, final DescribeConfigsOptions options)
		DescribeConsumerGroupsResult describeConsumerGroups(final Collection<String> groupIds, final DescribeConsumerGroupsOptions options)
		DescribeDelegationTokenResult describeDelegationToken(final DescribeDelegationTokenOptions options)
		DescribeLogDirsResult describeLogDirs(Collection<Integer> brokers, DescribeLogDirsOptions options)
		DescribeReplicaLogDirsResult describeReplicaLogDirs(Collection<TopicPartitionReplica> replicas, DescribeReplicaLogDirsOptions options)
		DescribeTopicsResult describeTopics(final Collection<String> topicNames, DescribeTopicsOptions options)
			* 获取指定topic的相信信息
			* 包括分区,isr,leader等信息

		ExpireDelegationTokenResult expireDelegationToken(final byte[] hmac, final ExpireDelegationTokenOptions options)
		ListConsumerGroupOffsetsResult listConsumerGroupOffsets(final String groupId, final ListConsumerGroupOffsetsOptions options)
		ListConsumerGroupsResult listConsumerGroups(ListConsumerGroupsOptions options)
		ListTopicsResult listTopics(final ListTopicsOptions options)
		Map<MetricName, ? extends Metric> metrics()
		RenewDelegationTokenResult renewDelegationToken(final byte[] hmac, final RenewDelegationTokenOptions options)
	
	# 主题的创建
		// 基本的配置
		Properties properties = new Properties();
		properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		try(KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(properties)){
			// 新建主题，设置名称partition数量，和副本数量
			NewTopic newTopics = new NewTopic("demo3", 1, (short) 1);
			
			CreateTopicsResult createTopicsResult = kafkaAdminClient.createTopics(Collections.singleton(newTopics));
			try {
				// 线程阻塞，直到返回所有主题的创建结果
				createTopicsResult.all().get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
-------------------------
AdminClientConfig		 |
-------------------------
	# 管理客户端的配置项
	bootstrap.servers
		* broker节点的地址
	client.id
	metadata.max.age.ms
	send.buffer.bytes
	receive.buffer.bytes
	reconnect.backoff.ms
	reconnect.backoff.max.ms
	retry.backoff.ms
	request.timeout.ms
	connections.max.idle.ms
	retries
	metrics.sample.window.ms
	metrics.num.samples
	metric.reporters
	metrics.recording.level
	client.dns.lookup
	security.protocol
	ssl.protocol
	ssl.provider
	ssl.cipher.suites
	ssl.enabled.protocols
	ssl.keystore.type
	ssl.keystore.location
	ssl.keystore.password
	ssl.key.password
	ssl.truststore.type
	ssl.truststore.location
	ssl.truststore.password
	ssl.keymanager.algorithm
	ssl.trustmanager.algorithm
	ssl.endpoint.identification.algorithm
	ssl.secure.random.implementation
	sasl.kerberos.service.name
	sasl.kerberos.kinit.cmd
	sasl.kerberos.ticket.renew.window.factor
	sasl.kerberos.ticket.renew.jitter
	sasl.kerberos.min.time.before.relogin
	sasl.login.refresh.window.factor
	sasl.login.refresh.window.jitter
	sasl.login.refresh.min.period.seconds
	sasl.login.refresh.buffer.seconds
	sasl.mechanism
	sasl.jaas.config
	sasl.client.callback.handler.class
	sasl.login.callback.handler.class
	sasl.login.class
