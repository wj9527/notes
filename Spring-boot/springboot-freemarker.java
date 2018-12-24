----------------------------------
freemarker						  |
----------------------------------
	# 官方依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-freemarker</artifactId>
		</dependency> 
	
	# 配置项
		spring.freemarker.allow-request-override=false 
		spring.freemarker.allow-session-override=false
		spring.freemarker.cache=false
			* 是否开启模版引擎缓存
		spring.freemarker.charset=UTF-8
			* 模版引擎的编码
		spring.freemarker.check-template-location=true
			* 检查目标目录是否存在
		spring.freemarker.content-type=text/html
			* Content-Type
		spring.freemarker.enabled=true
			* 启用
		spring.freemarker.expose-request-attributes=false
			* 是否允许访问request域中的model
		spring.freemarker.expose-session-attributes=false
			* 是否允许访问session域中的model
		spring.freemarker.expose-spring-macro-helpers=true
		spring.freemarker.prefer-file-system-access=true
		spring.freemarker.prefix= 
		spring.freemarker.request-context-attribute= request
			* 访问request域的别名
		spring.freemarker.settings.*= # Well-known FreeMarker keys which will be passed to FreeMarker's Configuration.
		spring.freemarker.suffix
			* # Suffix that gets appended to view names when building a URL.
		spring.freemarker.template-loader-path=classpath:/templates/
			*  一个或者多个模版引擎的路径
		spring.freemarker.view-names=
		spring.freemarker.template-encoding=UTF-8
	
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