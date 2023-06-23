package com.example.demowithtests.service.emailSevice;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @author Artem Kovalov on 23.06.2023
 */
@Service
public class SimpleMailMessageBuilder {

    public SimpleMailMessage getMessage(String toEmail, String name, EmailPattern emailPattern) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(emailPattern.getSubject());
        message.setText(getMessageBody(name, emailPattern));
        return message;
    }

    private String getMessageBody(String name, EmailPattern emailPattern) {
        return String.format("Dear %s!%s", name, emailPattern.getBody());
    }
}
