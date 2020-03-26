---------------------
Gson
---------------------
	# 构造函数
		public Gson() 
	
	# 实例方法
		Excluder excluder()
		FieldNamingStrategy fieldNamingStrategy()

		<T> T fromJson(JsonElement json, Class<T> classOfT)
		<T> T fromJson(JsonElement json, Type typeOfT)
		<T> T fromJson(JsonReader reader, Type typeOfT)
		<T> T fromJson(Reader json, Class<T> classOfT)
		<T> T fromJson(Reader json, Type typeOfT)
		<T> T fromJson(String json, Class<T> classOfT)
		<T> T fromJson(String json, Type typeOfT)

		<T> TypeAdapter<T> getAdapter(TypeToken<T> type)
		<T> TypeAdapter<T> getAdapter(Class<T> type)
		<T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory skipPast, TypeToken<T> type)
		boolean htmlSafe()
		GsonBuilder newBuilder()
		JsonReader newJsonReader(Reader reader)
		JsonWriter newJsonWriter(Writer writer)
		boolean serializeNulls()

		String toJson(JsonElement jsonElement)
		void toJson(JsonElement jsonElement, JsonWriter writer)
		void toJson(JsonElement jsonElement, Appendable writer)
		String toJson(Object src)
		void toJson(Object src, Appendable writer)
		String toJson(Object src, Type typeOfSrc)
		void toJson(Object src, Type typeOfSrc, JsonWriter writer)
		void toJson(Object src, Type typeOfSrc, Appendable writer)

		JsonElement toJsonTree(Object src)
		JsonElement toJsonTree(Object src, Type typeOfSrc)
			* 把指定的对象, 转换为jsonElement

		String toString()

---------------------
GsonBuilder
---------------------
	# Gson的构造Builder
	# 构造函数
		GsonBuilder()
	
	# 实例方法
		addDeserializationExclusionStrategy(ExclusionStrategy strategy)
		addSerializationExclusionStrategy(ExclusionStrategy strategy)
			* 设置序列化/反序列化时字段排除的策略
			* 如序列化/反序列化时不要某字段, 也可以可以采用@Expore代替
			* 抽象方法
				 boolean shouldSkipField(FieldAttributes f);
				 boolean shouldSkipClass(Class<?> clazz);
			
		GsonBuilder disableHtmlEscaping()
			* 禁止html编码
			* 默认会对html进行编码

		disableInnerClassSerialization()
			* 序列化时, 排除内部类

		enableComplexMapKeySerialization()
		excludeFieldsWithModifiers(int... modifiers)
			* 默认序列化的时候, 不会包含:transient, static 字段
			* 设置要包含的字段, 通过 Modifier 定义

		excludeFieldsWithoutExposeAnnotation()
			* 如果没有标识 @Expose(serialize = true, deserialize = true) , 则不会进行反序列化和序列化

		generateNonExecutableJson()
		registerTypeAdapter(Type type, Object typeAdapter)
			* 为指定的类型, 定制序列化/反序列化策略
			* typeAdapter需要 实现 JsonSerializer 或 JsonDeserializer 接口
				JsonSerializer
					public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context);
				JsonDeserializer
					public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)

		registerTypeAdapterFactory(TypeAdapterFactory factory)
		registerTypeHierarchyAdapter(Class<?> baseType, Object typeAdapter)
		serializeNulls()
			* 序列化 null 字段
			* 默认不会序列化 null 字段

		serializeSpecialFloatingPointValues()
			
		setDateFormat(int style)
		setDateFormat(int dateStyle, int timeStyle)
		setDateFormat(String pattern)
			* 设置日期的格式化

		setExclusionStrategies(ExclusionStrategy... strategies)
		setFieldNamingPolicy(FieldNamingPolicy namingConvention)
			* 设置字段名称的策略, 枚举
				FieldNamingPolicy
					IDENTITY						字段名称是啥, 就是啥
					UPPER_CAMEL_CASE				首字母大写
					UPPER_CAMEL_CASE_WITH_SPACES	首字母大写 并空格分割
					LOWER_CASE_WITH_UNDERSCORES		所有字母小写 下划线分割
					LOWER_CASE_WITH_DASHES			所有字母小写 中划线分割
					LOWER_CASE_WITH_DOTS			所有字符小写 点分割

		setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy)
			* 设置Gson字段解析策略, 对象字段 -> json字段名称
			* FieldNamingStrategy 是 接口, 唯一抽象方法
				String translateName(Field f);

		setLenient()
		setLongSerializationPolicy(LongSerializationPolicy serializationPolicy)
		setPrettyPrinting()
			* 序列化后, 格式化json字符串

		setVersion(double ignoreVersionsAfter)
			* 设置版本号,
			* 在对象,对象字段上标识的版本号 @Since(value), 只有小于等于这个值的才会被序列化

		create()


---------------------
JsonElement
---------------------
	# 抽象类
	# 实例方法
		JsonElement deepCopy();
		boolean isJsonArray()
		boolean isJsonObject()
		boolean isJsonPrimitive()
		boolean isJsonNull()
		JsonObject getAsJsonObject()
		JsonArray getAsJsonArray()
		JsonPrimitive getAsJsonPrimitive()
		JsonNull getAsJsonNull()
		boolean getAsBoolean()
		Number getAsNumber()
		String getAsString()
		double getAsDouble()
		float getAsFloat()
		long getAsLong()
		int getAsInt()
		byte getAsByte()
		BigDecimal getAsBigDecimal()
		BigInteger getAsBigInteger()
		short getAsShort()
		String toString()
	
	# 实现类
		JsonArray
		JsonNull
		JsonObject
		JsonPrimitive
		