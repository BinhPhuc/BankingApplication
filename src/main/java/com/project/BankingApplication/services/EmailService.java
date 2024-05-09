package com.project.BankingApplication.services;

import com.project.BankingApplication.entities.Email;
import com.project.BankingApplication.responses.EmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;
    @Override
    public EmailResponse sendSimpleEmail(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.beobinh@gmail.com");
        simpleMailMessage.setTo(email.getRecipient());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getMsgBody());
        javaMailSender.send(simpleMailMessage);
        return EmailResponse.
                builder()
                .sender("noreply.beobinh@gmail.com")
                .recipient(email.getRecipient())
                .subject(email.getSubject())
                .msgBody(email.getMsgBody())
                .build();
    }
}
