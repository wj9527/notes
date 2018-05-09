----------------------------
springboot的单元测试		|
----------------------------
	# 导入依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	
	# 测试代码
		@RunWith(SpringRunner.class)
		@SpringBootTest
		public class Test{
			@Test
			public void test(){
				
			}
		}