----------------------
task					|
----------------------
	# 注解配置
		* main程序入口配置:@EnableScheduling
		* 工作类配置:@Component
		* 工作方法配置:@Scheduled
			String cron() default "";
				* 定时任务表达式
			String zone() default "";
			long fixedDelay() default -1;
			String fixedDelayString() default "";
			long fixedRate() default -1;
			String fixedRateString() default "";
			long initialDelay() default -1;
			String initialDelayString() default "";

	
	#  处理异常
		No qualifying bean of type 'java.util.concurrent.ScheduledExecutorService' available
		* 好像是因为日志所产生的异常
		* 处理方式,修改日志配置文件
			<logger name="org.springframework.scheduling">
				<level value="info" />
			</logger>
	
	
	# 默认使用线程:scheduling 去执行
		* 可以使用 @Async 注解, 使用自定义的线程池去执行

	
	# 自定义配置, 实现配置接口 :SchedulingConfigurer
		@Configuration
		public class SchedulingConfiguration implements SchedulingConfigurer {

			@Override
			public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
				// 设置执行任务的线程池
				taskRegistrar.setScheduler(Executors.newScheduledThreadPool(20));
			}
		}
