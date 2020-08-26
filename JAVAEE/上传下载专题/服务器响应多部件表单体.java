---------------------------
��������Ӧ
---------------------------
	# ��Ҫ����
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
					// �� => �����ƣ������ݣ����ͣ�
					.addPart("name", new StringBody(UriUtils.encode("SpringBoot��������", StandardCharsets.UTF_8), ContentType.APPLICATION_FORM_URLENCODED))
					// JSON => �����ƣ�Json���ݣ����ͣ�
					.addPart("info", new StringBody("{\"site\": \"https://springboot.io\", \"year\": 2019}", ContentType.APPLICATION_JSON))
					// �ļ� => �����ƣ��ļ������ͣ��ļ����ƣ�
					.addBinaryBody("logo", new File("D:\\logo.png"), ContentType.IMAGE_PNG, "logo.png")
					.build();
		
		// ����ContentType
		response.setContentType(httpEntity.getContentType().getValue());
		
		// ��Ӧ�ͻ���
		httpEntity.writeTo(response.getOutputStream());
	}
}

---------------------------
�ͻ��˽���
---------------------------
	# ��Ҫ����
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
            
            // ����
            byte[] data = byteArrayOutputStream.toByteArray();
            
            String r = UriUtils.decode(new String(data, StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            
            System.out.println(r);
            
            nextPart = multipartStream.readBoundary();
        }
	}
}

