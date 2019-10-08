----------------------------
SchedulerFactory			|
----------------------------
	# 工厂类接口,  负责创建Scheduler实例对象
		Scheduler getScheduler() throws SchedulerException;
		Scheduler getScheduler(String schedName) throws SchedulerException;
		Collection<Scheduler> getAllSchedulers() throws SchedulerException;

	# 实现类有
		DirectSchedulerFactory
		StdSchedulerFactory
	

	
----------------------------
StdSchedulerFactory			|
----------------------------


		
----------------------------
DirectSchedulerFactory		|
----------------------------