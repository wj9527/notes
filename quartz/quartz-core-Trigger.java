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
		CalendarIntervalTrigger
		DailyTimeIntervalTrigger

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
			* 就绪后, 立即触发任务

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
			* name, 表示trigger唯一的名称
			* group, 表示trigger所属的分组
			
			* 如果没设置 group, 默认为: DEFAULT

		TriggerBuilder<T> withPriority(int triggerPriority)
			* 优先级, 本质上就是设置了线程的优先级
			* 如果没有为trigger设置优先级，trigger使用默认优先级，值为5
			* priority属性的值可以是任意整数，正数、负数都可以
			* 注意：只有同时触发的trigger之间才会比较优先级。


		<SBT extends T> TriggerBuilder<SBT> withSchedule(ScheduleBuilder<SBT> schedBuilder)
			* 创建调度规则

----------------------------
ScheduleBuilder				|
----------------------------
	# 调度接口的工厂接口, 抽象方法
		  protected abstract MutableTrigger build();

	# 子类
		CalendarIntervalScheduleBuilder
		CronScheduleBuilder
		DailyTimeIntervalScheduleBuilder
		SimpleScheduleBuilder


----------------------------
SimpleTrigger				|
----------------------------
	# 通过 SimpleScheduleBuilder 创建
	# 静态工厂方法
		SimpleScheduleBuilder simpleSchedule()

		SimpleScheduleBuilder repeatMinutelyForever()
		SimpleScheduleBuilder repeatMinutelyForever(int minutes)
			* 按照多少分重复执行, 默认1分

		SimpleScheduleBuilder repeatSecondlyForever() 
		SimpleScheduleBuilder repeatSecondlyForever(int seconds)
			* 按照多少秒重复执行, 默认1秒

		SimpleScheduleBuilder repeatHourlyForever()
		SimpleScheduleBuilder repeatHourlyForever(int hours)
			* 按照多少小时重复执行, 默认1小时

		SimpleScheduleBuilder repeatMinutelyForTotalCount(int count)
		SimpleScheduleBuilder repeatMinutelyForTotalCount(int count, int minutes)

		SimpleScheduleBuilder repeatSecondlyForTotalCount(int count)
		SimpleScheduleBuilder repeatSecondlyForTotalCount(int count, int seconds)

		SimpleScheduleBuilder repeatHourlyForTotalCount(int count)
		SimpleScheduleBuilder repeatHourlyForTotalCount(int count, int hours)

	# 实例方法
		MutableTrigger build()
		SimpleScheduleBuilder withIntervalInMilliseconds(long intervalInMillis)
		SimpleScheduleBuilder withIntervalInMilliseconds(long intervalInMillis)
		SimpleScheduleBuilder withIntervalInSeconds(int intervalInSeconds)
		SimpleScheduleBuilder withIntervalInMinutes(int intervalInMinutes)
		SimpleScheduleBuilder withIntervalInHours(int intervalInHours)
		SimpleScheduleBuilder withRepeatCount(int triggerRepeatCount)
		SimpleScheduleBuilder repeatForever()
		SimpleScheduleBuilder withMisfireHandlingInstructionIgnoreMisfires()
		SimpleScheduleBuilder withMisfireHandlingInstructionFireNow() 
		SimpleScheduleBuilder withMisfireHandlingInstructionNextWithExistingCount()
		SimpleScheduleBuilder withMisfireHandlingInstructionNextWithRemainingCount()
		SimpleScheduleBuilder withMisfireHandlingInstructionNowWithExistingCount()
		SimpleScheduleBuilder withMisfireHandlingInstructionNowWithRemainingCount()
		


----------------------------
CronTrigger					|
----------------------------

----------------------------
TriggerKey					|
----------------------------
	# 描述trigger的name和grouo属性对象
		
	# 跟JobKey一摸一样, 除了类名不同

