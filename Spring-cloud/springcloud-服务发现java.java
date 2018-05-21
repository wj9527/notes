------------------------
服务发现				|
------------------------
	# 其实就是,可以通过一个接口来获取到服务的信息
	# DiscoveryClient
	# 伪代码

		@Autowired
		private DiscoveryClient client;
		
		//获取注册中心里面的微服务信息
		client.getService();
		
		List<ServiceInstance> instances = client.getInstances("USER-SERVICE");

		for(ServiceInstance serviceInstance : instances){
			serviceInstance.getServiceId();
			serviceInstance.getHost();
			serviceInstance.getPort();
			serviceInstance.getUri();
		}