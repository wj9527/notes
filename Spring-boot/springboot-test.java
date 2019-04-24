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
		import io.springboot.jpa.JpaApplication;
		import io.springboot.jpa.dto.UserDTO;
		import io.springboot.jpa.repository.UserRepository;
		import io.springboot.jpa.service.UserService;
		import junit.framework.TestCase;
		import org.junit.Test;
		import org.junit.runner.RunWith;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.test.context.SpringBootTest;
		import org.springframework.test.context.junit4.SpringRunner;



		@RunWith(SpringRunner.class)
		// 设置@SpringBootApplication 类
		@SpringBootTest(classes = JpaApplication.class)
		public class JpaTest {

			// 可以使用IOC
			@Autowired
			private UserRepository userRepository;

			@Test
			public void test(){
				//TODO 执行测试代码

				// 断言非空
				TestCase.assertNotNull();
				// 断言equals
				TestCase.assertEquals();
			}
		}