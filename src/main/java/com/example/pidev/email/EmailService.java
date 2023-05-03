package com.example.pidev.email;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



@Service
@AllArgsConstructor


public class EmailService implements EmailSender {


    private final JavaMailSender mailSender;

    private final static Logger LOGGER = (Logger) LoggerFactory
            .getLogger(EmailService.class);




    @Override
    @Async
    public void send(String to, String mail) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(mail, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("yussef@supporteam.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}