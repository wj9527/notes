----------------------------------
freemarker						  |
----------------------------------
	# 官方依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-freemarker</artifactId>
		</dependency> 
	
	# 配置项
spring:
  freemarker:
    # 是否开启，默认:true
    enabled: true
    # 是否开启模版引擎缓存，默认:false
    cache: true
    # 默认:text/html
    content-type: text/html
    # 编码，默认: urf-8
    charset: utf-8
    # 可以解析的视图列表，数组
    view-names:
      - views
    # 是否检查模板引擎目录是否存在，默认:true
    check-template-location: true
    # 视图前缀
    prefix: /temp
    # 视图后缀 
    suffix: .ftl
    # 使用指定的属性访问 request 域中的数据
    request-context-attribute: req
    # 默认:false
    expose-request-attributes: true
    # 默认:false
    expose-session-attributes: true
    # 默认:false
    allow-request-override: true
    # 默认:true
    expose-spring-macro-helpers: true
    # 默认 false
    allow-session-override: false
    # 模板引擎加载目录，默认:classpath:/templates/
    template-loader-path:
      - classpath:/templates/
    # 默认:true
    prefer-file-system-access: true
    # freemarker中Configuration设置的配置。也就是定义在:freemarker.coreConfigurable 中的那些静态变量值
    settings:
      datetime_format: yyyy-MM-dd HH:mm:ss
	
	# 一般设置
spring:
  freemarker:
    enabled: true
    request-context-attribute: request
    expose-request-attributes: true
    expose-session-attributes: true
    suffix: .ftl
    content-type: text/html
    # 开发环境模版,模板引擎不缓存
    cache: false
    template-loader-path:
      - classpath:/templates/
    charset: UTF-8
    template-encoding: UTF-8
    settings:
      datetime_format: yyyy-MM-dd HH:mm:ss
      # 模板引擎检测模板变化时间差
      # template_update_delay: 0

----------------------------------
配置							  |
----------------------------------
	import javax.annotation.PostConstruct;
	import javax.servlet.ServletContext;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Configuration;

	import freemarker.template.TemplateModelException;

	@Configuration
	public class FreemarkerConfiguration {

		@Autowired
		private ServletContext servletContext;
		
		@Autowired
		private freemarker.template.Configuration configuration;
		
		@PostConstruct
		public void configuration() throws TemplateModelException {
			//自定义配置信息
			this.configuration.setSharedVariable("ctx", this.servletContext.getContextPath());
		}
	}