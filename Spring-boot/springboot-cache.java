--------------------
springboot-cache	|
--------------------
	# 参考
		https://blog.csdn.net/u012373815/article/details/54564076

	# 依赖
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
	
	# 开启
		@EnableCaching

	# spring 缓存支持,抽象出来了一个 CacheManager和Cache接口
		org.springframework.cache.CacheManager
		org.springframework.cache.Cache
	
	# spring支持的缓存框架
		SimpleCacheManager
			* 直接使用了一个 Collection 来存储
		ConcurrentMapCache
			* 使用 ConcurrentMap 来存储
		EhCacheCacheManager
			* 使用EhCache作为缓存技术
		RedisCacheManager
			* 使用Redis作为缓存技术
	
	# 自定义缓存中的一些配置
		* 继承:CachingConfigurerSupport,覆写方法
			import java.lang.reflect.Method;
			import org.springframework.cache.annotation.CachingConfigurerSupport;
			import org.springframework.cache.annotation.EnableCaching;
			import org.springframework.cache.interceptor.KeyGenerator;
			import org.springframework.context.annotation.Configuration;
			/**
			 * 
			 * @author KevinBlandy
			 *
			 */
			@EnableCaching
			@Configuration
			public class RedisCacheConfiguration extends CachingConfigurerSupport{

				//自定义key生成策略
				@Override
				public KeyGenerator keyGenerator() {
					KeyGenerator generator = new KeyGenerator() {
						@Override
						public Object generate(Object target, Method method, Object... params) {
							return target.getClass().getSimpleName() + ":" + method.getName();
						}
					};
					return generator;
				}
			}
	
--------------------
声明式注解			|
--------------------
	@Cacheable
		# 如果存在缓存,直接返回,不存在调用方法获取计算结果,存入缓存
		# 当标记在一个类上时则表示该类所有的方法都是支持缓存的
		# 属性
			@AliasFor("cacheNames")
			String[] value() default {};

			@AliasFor("value")
			String[] cacheNames() default {};

			String key() default "";
			String keyGenerator() default "";
			String cacheManager() default "";
			String cacheResolver() default "";
			String condition() default "";
			String unless() default "";

			boolean sync() default false;

	@CachePut
		# 不论怎么样,都会把方法返回值放入缓存中
		# CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果,而是每次都会执行该方法,并将执行结果以键值对的形式存入指定的缓存中
		# 属性
			@AliasFor("cacheNames")
			String[] value() default {};

			@AliasFor("value")
			String[] cacheNames() default {};

			String key() default "";
			String keyGenerator() default "";
			String cacheManager() default "";
			String cacheResolver() default "";
			String condition() default "";
			String unless() default "";
	
	@CacheEvict
		# 标注在需要清除缓存元素的方法或类上的
		# 当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作
		# 属性
			@AliasFor("cacheNames")
			String[] value() default {};
			@AliasFor("value")
			String[] cacheNames() default {};
			String key() default "";
			String keyGenerator() default "";
			String cacheManager() default "";
			String cacheResolver() default "";
			String condition() default "";
			boolean allEntries() default false;
				* 表示是否需要清除缓存中的所有元素,默认为false,表示不需要
				* 有的时候需要Cache一下清除所有的元素,这比一个一个清除元素更有效率

			boolean beforeInvocation() default false;
				* 清除操作默认是在对应方法成功执行之后触发的,即方法如果因为抛出异常而未能成功返回时也不会触发清除操作
				* 该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素

	@Caching
		# 组合注解,把多个注解整合到一个上
		# 属性
			Cacheable[] cacheable() default {};
			CachePut[] put() default {};
			CacheEvict[] evict() default {};
	
	# 使用自定义注解
		* Spring允许我们在配置可缓存的方法时使用自定义的注解
		* 前提是自定义的注解上必须使用对应的注解进行标注
			@Target({ElementType.TYPE, ElementType.METHOD})
			@Retention(RetentionPolicy.RUNTIME)
			@Cacheable(value="users")		//(*^__^*) 
			public @interface MyCacheable {

			}


--------------------
使用redis			|
--------------------
	# 开启redis的start(具体看redis的笔记)
		<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-data-redis</artifactId>
    	</dependency>

	# 就这样
	

--------------------
Ehcache				|
--------------------
	# 依赖
		 <dependency>
            <groupId>net.sf.ehcache</groupId>
            <artifactId>ehcache</artifactId>
        </dependency>

		