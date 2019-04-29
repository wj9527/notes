-------------------------------
email							|
-------------------------------
	# µº»Î“¿¿µ
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>
	



#--------------------------------------
# email	config					
#-------------------------------------

spring:
  mail:
    host: smtp.exmail.qq.com
    username: 
    password: 
    port: 587
    default-encoding: UTF-8
    protocol: smtp
    properties:
      mail:
        smtp:
          connectiontimeout: 5000
          timeout: 3000
          writetimeout: 5000
          auth: true
          starttls:
            enable: true


#--------------------------------------
# MailService					
#-------------------------------------


import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeUtility;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;
    
    @Value("${spring.mail.sender}")
    private String sender;

    public void sendHTMLMail(String to,String title,String content) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(MimeUtility.encodeText(sender) + "<" + this.username + ">"));
        helper.setTo(to);
        helper.setSubject(title);
        helper.setText(content, true);
        javaMailSender.send(message);
    }
}
