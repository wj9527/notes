
-------------------
quartz.properties	|
-------------------
	# 放在classpath下

-------------------
配置项				|
-------------------
配置项																		默认值
org.quartz.scheduler.instanceName											QuartzScheduler
org.quartz.scheduler.instanceId												NON_CLUSTERED
org.quartz.scheduler.instanceIdGenerator.class								org.quartz.simpl.SimpleInstanceIdGenerator
org.quartz.scheduler.threadName												instanceName+ '_QuartzSchedulerThread'
org.quartz.scheduler.makeSchedulerThreadDaemon								false
org.quartz.scheduler.threadsInheritContextClassLoaderOfInitializer			false
org.quartz.scheduler.idleWaitTime											30000
org.quartz.scheduler.dbFailureRetryInterval									15000
org.quartz.scheduler.classLoadHelper.class									org.quartz.simpl.CascadingClassLoadHelper
org.quartz.scheduler.jobFactory.class										org.quartz.simpl.PropertySettingJobFactory
org.quartz.context.key.SOME_KEY												none
org.quartz.scheduler.userTransactionURL										'java:comp/UserTransaction'
org.quartz.scheduler.wrapJobExecutionInUserTransaction						false
org.quartz.scheduler.skipUpdateCheck										false
org.quartz.scheduler.batchTriggerAcquisitionMaxCount						1
org.quartz.scheduler.batchTriggerAcquisitionFireAheadTimeWindow				0


