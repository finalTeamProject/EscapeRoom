package com.noexit.app;  

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailTest {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
        
        
        Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(
        				"god5228god@gmail.com",
                        "" // 여기 직접 입력
        				);
        	}
        
        });


        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("god5228god@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("god5228god@gmail.com"));
        message.setSubject("테스트");
        message.setText("테스트 메일");
        Transport.send(message);
        System.out.println("발송 성공!");
    }
}