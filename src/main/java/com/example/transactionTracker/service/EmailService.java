package com.example.transactionTracker.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        log.info("Mail con Stringa inviata");
    }

//    public void sendHtmlEmail() throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//
//        message.setFrom(new InternetAddress("sender@example.com"));
//        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
//        message.setSubject("Test email from Spring");
//
//        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
//                "<p>It can contain <strong>HTML</strong> content.</p>";
//        message.setContent(htmlContent, "text/html; charset=utf-8");
//        mailSender.send(message);
//        log.info("Mail con HTML inviata");
//
//    }


}
