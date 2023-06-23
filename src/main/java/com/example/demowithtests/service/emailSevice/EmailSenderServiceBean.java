package com.example.demowithtests.service.emailSevice;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class EmailSenderServiceBean implements EmailSenderService {

    private final JavaMailSender mailSender;
    private final SimpleMailMessageBuilder messageBuilder;

    @Override
    public void sendEmail(String toEmail, String name, EmailPattern emailPattern) throws MailException {
        SimpleMailMessage message = messageBuilder.getMessage(toEmail, name, emailPattern);
//        mailSender.send(message);
        log.info("Mail to {} sent successfully", toEmail);
    }
}
