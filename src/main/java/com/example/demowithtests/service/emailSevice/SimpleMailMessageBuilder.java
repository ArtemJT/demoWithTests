package com.example.demowithtests.service.emailSevice;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author Artem Kovalov on 23.06.2023
 */
public class SimpleMailMessageBuilder {

    public static SimpleMailMessage getMessage(String toEmail, String name, EmailPattern emailPattern) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(emailPattern.getSubject());
        message.setText(getMessageBody(name, emailPattern));
        return message;
    }

    private static String getMessageBody(String name, EmailPattern emailPattern) {
        return String.format("Dear %s!%s", name, emailPattern.getBody());
    }
}
