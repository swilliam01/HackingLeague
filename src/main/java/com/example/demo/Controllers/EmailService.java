package com.example.demo.Controllers;

import com.example.demo.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Message;
import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService extends Email {
    private TemplateEngine templateEngine;

    @Autowired
    Environment env;

    @Autowired
    public EmailService(TemplateEngine templateEngine){
        this.templateEngine= templateEngine;
    }

    private Properties GetProperties(){
        Properties props = new Properties();

        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));

        return props;
    }

    private Session Getsession(){
        //Email account credentials
        final String username  = "JavaMailTestMC@gmail.com";
        final String password = "helloworldpasswordtest";

        //Create session with username and password
        Session session = Session.getInstance(GetProperties(),
                new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    public String BuildTemplateWithContent(String message){
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailtemplate", context);
    }
    public void SendSimpleEmail(String emailTo, String message){
        try{
            Message mimeMessage = new MimeMessage(Getsession());
            String content = BuildTemplateWithContent(message);

            //The email address you are sending from
            mimeMessage.setFrom(new InternetAddress("JavaMailTestMC@gmail.com"));

            //The email address(es) you are sending the email to
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));

            //Email Subject
            mimeMessage.setSubject("Test Templated email");

            //Email content
            mimeMessage.setContent(content, "text/html; charset=utf-8");

            //Send email
            Transport.send(mimeMessage);

        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }

}
