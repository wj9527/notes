--------------------------
springfox ����
--------------------------
	ApiInfo
	Docket

--------------------------
springfox mvc ������
--------------------------
	@Configuration
	public class WebMvcConfigration implements WebMvcConfigurer {
		
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/swagger-ui/**")
					.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
					.resourceChain(false);
		}

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/swagger-ui/")
					.setViewName("forward:/swagger-ui/index.html");
		}
	}




----------------------------------
swagger-Configuration
----------------------------------
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket docket() {

		ApiInfo apiInfo = new ApiInfoBuilder().version("0.0.0.1") // �汾��
				.contact(new Contact("KevinBlandy", "https://springboot.io", "kevinblandy.cn@gmail.com")) // ��ϵ��
				.description("�ÿ����Ե�") // ����
				.extensions(null) // ��Ӷ����չ
				.license("���֤") // api���֤
				.licenseUrl("https://springboot.io?license") // api���֤��ַ
				.termsOfServiceUrl("https://springboot.io/service") // ���������ַ
				.title("swagger �ӿ��ĵ�") // ����
				.build();

		// �����ĵ����ʹ��� Docket
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Swagger �ӿ��ĵ�")
				// api info
				.apiInfo(apiInfo)
				// ����
				.enable(true)
				// ɨ����͹���
				.select().apis(RequestHandlerSelectors.basePackage("io.springboot.swagger.controller"))
				.paths(PathSelectors.any()).build();

	}
}