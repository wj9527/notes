---------------------------
服务器响应
---------------------------
	# 需要依赖
		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpmime</artifactId>
		</dependency>

import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping
	public void test (HttpServletResponse response) throws Exception {
		
		HttpEntity httpEntity = MultipartEntityBuilder.create()
					// 表单 => （名称，表单数据，类型）
					.addPart("name", new StringBody(UriUtils.encode("SpringBoot中文社区", StandardCharsets.UTF_8), ContentType.APPLICATION_FORM_URLENCODED))
					// JSON => （名称，Json数据，类型）
					.addPart("info", new StringBody("{\"site\": \"https://springboot.io\", \"year\": 2019}", ContentType.APPLICATION_JSON))
					// 文件 => （名称，文件，类型，文件名称）
					.addBinaryBody("logo", new File("D:\\logo.png"), ContentType.IMAGE_PNG, "logo.png")
					.build();
		
		// 设置ContentType
		response.setContentType(httpEntity.getContentType().getValue());
		
		// 响应客户端
		httpEntity.writeTo(response.getOutputStream());
	}
}

---------------------------
客户端解析
---------------------------
	# 需要依赖
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.4</version>
		</dependency>

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.fileupload.MultipartStream;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

public class MultipartTest {
	public static void main(String[] args) throws IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Resource> responseEntity = restTemplate.getForEntity("http://localhost:8081/test", Resource.class);

		MediaType mediaType = responseEntity.getHeaders().getContentType();
		System.out.println(mediaType);
		
		System.out.println(mediaType.getParameters());
		
		MultipartStream multipartStream = new MultipartStream(responseEntity.getBody().getInputStream(), mediaType.getParameter("boundary").getBytes(), 4096, null);

        boolean nextPart = multipartStream.skipPreamble();
        
        while (nextPart) {
        	
            String header = multipartStream.readHeaders();
            System.out.println("Headers:==================");
            
            System.out.println(header);
            System.out.println("Body:==================");
            
            ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
            multipartStream.readBodyData(byteArrayOutputStream);
            
            // 数据
            byte[] data = byteArrayOutputStream.toByteArray();
            
            String r = UriUtils.decode(new String(data, StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            
            System.out.println(r);
            
            nextPart = multipartStream.readBoundary();
        }
	}
}

