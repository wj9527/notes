-------------------------------
email							|
-------------------------------
	# 导入依赖
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>
	



#--------------------------------------
# email	config					
#-------------------------------------

spring.mail.host=smtp.exmail.qq.com
spring.mail.username=
spring.mail.password=
spring.mail.port=25
spring.mail.default-encoding=UTF-8
spring.mail.protocol=smtp

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true


#--------------------------------------
# MailService					
#-------------------------------------


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class MailService {

    private static String MAIL_USER = null;

    static {
        try {
			// 发件人/收件人地址包含了中文,需要进行编码
			// name<address> -> KevinBlabdy<747692844@qq.com>
            MAIL_USER = javax.mail.internet.MimeUtility.encodeText("springboot中文社区");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送html邮件
     * @param to
     * @param title
     * @param content
     * @throws MessagingException
     */
    public void sendHTMLMail(String to,String title,String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(MAIL_USER + "<" + this.from + ">"));
        helper.setTo(to);
        helper.setSubject(title);
        helper.setText(content, true);
        javaMailSender.send(message);
    }
}
