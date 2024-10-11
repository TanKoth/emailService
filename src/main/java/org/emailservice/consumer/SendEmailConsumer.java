package org.emailservice.consumer;

import org.emailservice.dto.EmailDto;
import org.emailservice.util.EmailUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Component
public class SendEmailConsumer {

    private ObjectMapper objectMapper;

    public SendEmailConsumer(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "user_SignUp",groupId = "emailService")
    public  void sendEmail(String message){
        try{
            EmailDto emailDto = objectMapper.readValue(message,EmailDto.class);

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); //trust the SMTP server
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            //Enable JavaMail debugging
            props.put("mail.debug", "true");

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailDto.getFrom(), "jyaxlkiiwkkdmksq");
                }
            };
            Session session = Session.getInstance(props, auth);

            EmailUtil.sendEmail(session, emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
