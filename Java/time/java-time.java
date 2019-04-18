------------------------
java.time				|
------------------------
	# 新的日期API
	# 包结构
		java.time
			|-LocalDate				//本地日期
			|-LocalTime				//本地时间
			|-LocalDateTime			//本地日期时间
			|-Instant				//时间戳
			|-DayOfWeek
		java.time.chrono
		java.time.format
			|-DateTimeFormatter
			|-DateTimeFormatterBuilder
			|-DecimalStyle
		java.time.temporal
		java.time.zone
		

------------------------
java.time demos			|
------------------------

# 获取指定日期的最大/小LocalDateTime
	LocalDateTime dateTimeMin = LocalDateTime.of(LocalDate.now(),LocalTime.MIN);
	LocalDateTime dateTimeMax = LocalDateTime.of(LocalDate.now(),LocalTime.MAX);


# 使用星期去修改指定的日期
	LocalDate.now().with(DayOfWeek.MONDAY)
	LocalDate.now().with(DayOfWeek.SUNDAY)




