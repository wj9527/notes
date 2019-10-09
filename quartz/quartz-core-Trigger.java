----------------------------
Trigger						|
----------------------------
	# 接口内部的枚举和常量
		public enum TriggerState { NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED }
		public enum CompletedExecutionInstruction { NOOP, RE_EXECUTE_JOB, SET_TRIGGER_COMPLETE, DELETE_TRIGGER, 
			SET_ALL_JOB_TRIGGERS_COMPLETE, SET_TRIGGER_ERROR, SET_ALL_JOB_TRIGGERS_ERROR }
		public static final int MISFIRE_INSTRUCTION_SMART_POLICY = 0;
		public static final int MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY = -1;
		public static final int DEFAULT_PRIORITY = 5;

	# 接口方法
		public TriggerKey getKey();
		public JobKey getJobKey();
		
		public String getDescription();

		public String getCalendarName();

		public JobDataMap getJobDataMap();
		public int getPriority();

		public boolean mayFireAgain();
		public Date getStartTime();

		public Date getEndTime();

		public Date getNextFireTime();

		public Date getPreviousFireTime();

		public Date getFireTimeAfter(Date afterTime);

		public Date getFinalFireTime();

		public int getMisfireInstruction();
		public TriggerBuilder<? extends Trigger> getTriggerBuilder();
		
		public ScheduleBuilder<? extends Trigger> getScheduleBuilder();

		public boolean equals(Object other);
		
		public int compareTo(Trigger other);


	# 常用的子接口
		SimpleTrigger
		CronTrigger
	
	# 错过触发(misfire Instructions)
		
----------------------------
TriggerBuilder				|
----------------------------
	# Trigger 工厂类
	
	# 静态方法
		static TriggerBuilder<Trigger> newTrigger()
		
	# 实例方法
		T build()
		TriggerBuilder<T> endAt(Date triggerEndTime)
			* 表示trigger失效的时间点

		TriggerBuilder<T> forJob(JobDetail jobDetail)
		TriggerBuilder<T> forJob(JobKey keyOfJobToFire)
		TriggerBuilder<T> forJob(String jobName)
		TriggerBuilder<T> forJob(String jobName, String jobGroup)
		TriggerBuilder<T> modifiedByCalendar(String calName)
		TriggerBuilder<T> startAt(Date triggerStartTime)
			* 设置trigger第一次触发的时间

		TriggerBuilder<T> startNow()
		TriggerBuilder<T> usingJobData(JobDataMap newJobDataMap)
		TriggerBuilder<T> usingJobData(String dataKey, Boolean value)
		TriggerBuilder<T> usingJobData(String dataKey, Double value)
		TriggerBuilder<T> usingJobData(String dataKey, Float value)
		TriggerBuilder<T> usingJobData(String dataKey, Integer value)
		TriggerBuilder<T> usingJobData(String dataKey, Long value)
		TriggerBuilder<T> usingJobData(String dataKey, String value)

		TriggerBuilder<T> withDescription(String triggerDescription)

		TriggerBuilder<T> withIdentity(String name)
		TriggerBuilder<T> withIdentity(String name, String group)
		TriggerBuilder<T> withIdentity(TriggerKey triggerKey)
		TriggerBuilder<T> withPriority(int triggerPriority)
			* 优先级, 本质上就是设置了线程的优先级
			* 如果没有为trigger设置优先级，trigger使用默认优先级，值为5
			* priority属性的值可以是任意整数，正数、负数都可以
			* 注意：只有同时触发的trigger之间才会比较优先级。


		<SBT extends T> TriggerBuilder<SBT> withSchedule(ScheduleBuilder<SBT> schedBuilder)

----------------------------
SimpleTrigger				|
----------------------------


----------------------------
CronTrigger					|
----------------------------