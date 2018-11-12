-----------------------------
Spring boot 使用 fastjson	 |
-----------------------------
	# spring 默认的json处理框架是 jackson
	# fastjson 是阿里巴巴的一个高性能json序列化框架
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.24</version>
		</dependency>
	
-----------------------------
Spring boot 方法一			 |
-----------------------------
	1,实现 WebMvcConfigurer
	2,覆盖方法 configureMessageConverters

	# 代码
		@Configuration
		public class WebMvcConfiguration implements WebMvcConfigurer {
			@Override
			public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
				WebMvcConfigurer.super.configureMessageConverters(converters);

				FastJsonConfig fastJsonConfig = new FastJsonConfig();
				fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
				fastJsonConfig.setCharset(StandardCharsets.UTF_8);
				fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);

				FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
				fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
				fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

				converters.add(fastJsonHttpMessageConverter);
			}
		}


-----------------------------
Spring boot 方法二			 |
-----------------------------
	# 以组件的形式把 fastjson 的消息转换器.加入IOC
	# 代码
		/**
		 * Created by KevinBlandy on 2017/2/25 16:47
		 */
		@Configuration
		public class HttpMessageConverterConfiguration {
			/**
			 * FastJsonpHttpMessageConverter4
			 * @return
			 */
			@Bean
			public HttpMessageConverters httpMessageConverter(){
				FastJsonpHttpMessageConverter4 fastJsonpHttpMessageConverter4 = new FastJsonpHttpMessageConverter4();
				fastJsonpHttpMessageConverter4.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
				FastJsonConfig fastJsonConfig = new FastJsonConfig();
				fastJsonConfig.setCharset(StandardCharsets.UTF_8);
				fastJsonConfig.setSerializerFeatures(
						SerializerFeature.PrettyFormat,				//格式化
						SerializerFeature.WriteMapNullValue,		//输出null字段
						SerializerFeature.QuoteFieldNames,			//使用双引号
						SerializerFeature.WriteNullListAsEmpty);	//把null集合/数组输出为[]
				fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
				fastJsonpHttpMessageConverter4.setFastJsonConfig(fastJsonConfig);
				return new HttpMessageConverters(fastJsonpHttpMessageConverter4);
			}
			
			/**
			 * 跨域支持
			 * @return
			 */
			@Bean
			public FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice(){
				FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice = new FastJsonpResponseBodyAdvice("callback","jsonp");
				return fastJsonpResponseBodyAdvice;
			}
		}
		* 代码逻辑其实跟上面差不多


		
	
