package com.app.patientcard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MailClient {

    @Value("${spring.mail.username}")
    private String FROM;
    private JavaMailSender mailSender;

    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public boolean prepareAndSend(String[] recipient, String subject, String message, Map<String, byte[]> attachments) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(FROM);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
            if(!attachments.isEmpty()){
                for (Map.Entry<String, byte[]> entry : attachments.entrySet()) {
                    messageHelper.addAttachment(entry.getKey(),new ByteArrayResource(entry.getValue()));
                }
            }
        };
        try {
            mailSender.send(messagePreparator);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
