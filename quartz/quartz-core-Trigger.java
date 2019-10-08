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

----------------------------
TriggerBuilder				|
----------------------------
	# Trigger 工厂类
	
	# 静态方法
		static TriggerBuilder<Trigger> newTrigger()
		
	# 实例方法
		T build()
		TriggerBuilder<T> endAt(Date triggerEndTime)
		TriggerBuilder<T> forJob(JobDetail jobDetail)
		TriggerBuilder<T> forJob(JobKey keyOfJobToFire)
		TriggerBuilder<T> forJob(String jobName)
		TriggerBuilder<T> forJob(String jobName, String jobGroup)
		TriggerBuilder<T> modifiedByCalendar(String calName)
		TriggerBuilder<T> startAt(Date triggerStartTime)
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

		<SBT extends T> TriggerBuilder<SBT> withSchedule(ScheduleBuilder<SBT> schedBuilder)


