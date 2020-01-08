------------------------
Querydsl				|
------------------------
	# 官网
		http://www.querydsl.com/

	
	# 依赖 & 插件配置
		<!-- https://mvnrepository.com/artifact/com.querydsl/querydsl-jpa -->
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-apt</artifactId>
			<scope>provided</scope>
		</dependency>


		<build>
			<plugins>
				<plugin>
					<groupId>com.mysema.maven</groupId>
					<artifactId>apt-maven-plugin</artifactId>
					<version>1.1.3</version>
					<executions>
						<execution>
							<goals>
								<goal>process</goal>
							</goals>
							<configuration>
								<outputDirectory>target/generated-sources/java</outputDirectory>
								<processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
							</configuration>
						</execution>
					</executions>
				</plugin>
			</plugins>
		</build>
		
		* 需要先配置整合jpa
	
	# 配置 JPAQueryFactory
		import javax.persistence.EntityManager;

		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;

		import com.querydsl.jpa.impl.JPAQueryFactory;

		@Configuration
		public class JPAQueryFactoryConfiguration {

			@Bean
			public JPAQueryFactory jPAQueryFactory (@Autowired EntityManager entityManager) {
				return new JPAQueryFactory(entityManager);
			}
		}
	
	# Repository 实现接口: QuerydslPredicateExecutor
		* 通过该接口, 给 Repository 扩展queryDsl的检索api
	
	# 在需要 JPAQueryFactory 的地方, 注入
		@Autowired
		JPAQueryFactory jPAQueryFactory
	
