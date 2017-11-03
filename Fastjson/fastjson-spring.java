--------------------
FastJson-整合springmvc|
--------------------
	# 普通转换器
		FastJsonHttpMessageConverter
		FastJsonHttpMessageConverter4

	# 支持JSONP的转换器
		FastJsonpHttpMessageConverter4
		* 需要配置  FastJsonpResponseBodyAdvice 来指定JSONP的请求参数


	  <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="com.alibaba.fastjson.support.spring.FastJsonpHttpMessageConverter4">
                <property name="supportedMediaTypes">
                    <list>
                        <value>application/json</value>
                    </list>
                </property>
                <property name="fastJsonConfig">
                    <bean class="com.alibaba.fastjson.support.config.FastJsonConfig">
                        <property name="charset" value="UTF-8"/>
                        <property name="serializerFeatures">
                            <array>
                                <!-- 输出null字段 -->
                                <value>WriteMapNullValue</value>
                                <!-- 输出key使用双引号 -->
                                <value>QuoteFieldNames</value>
                                <!-- 空集合输出[] -->
                                <value>WriteNullListAsEmpty</value>
                            </array>
                        </property>
                        <!-- 日期格式 -->
                        <property name="dateFormat" value="yyyy-MM-dd HH:mm:ss"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
	
	<!--  jsonp 跨域 支持-->
    <bean class="com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice">
        <constructor-arg>
            <list>
                <value>callback</value>
                <value>jsonp</value>
            </list>
        </constructor-arg>
    </bean>