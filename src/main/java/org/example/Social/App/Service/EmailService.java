package org.example.Social.App.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


//Class to send mails based on details from application properties and data provided to it.
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String receiver, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiver);
        message.setText(text);
        message.setSubject("JWT Token");
        javaMailSender.send(message);
        System.out.println("Email sent successfully");
    }
}
