package com.example.demowithtests.service.emailSevice;

import org.springframework.mail.MailException;

public interface EmailSenderService {

    void sendEmail(String toEmail, String name, EmailPattern emailPattern) throws MailException;
}
