package com.example.filRouge.helpers.email;
import com.example.filRouge.entities.Member;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmailService {
    final private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(Member member, String password) throws MessagingException, IOException {

            MimeMessage message = mailSender.createMimeMessage();

            message.setFrom(new InternetAddress(from));
            message.setRecipients(MimeMessage.RecipientType.TO, member.getEmail());
            message.setSubject("GENERATE PASSWORD");

            String htmlTemplate = new String(Files.readAllBytes(Paths.get("password.html")));

            htmlTemplate = htmlTemplate.replace("${name}", member.getName());
            htmlTemplate = htmlTemplate.replace("${email}", member.getEmail());
            htmlTemplate = htmlTemplate.replace("${password}", password);

            message.setContent(htmlTemplate, "text/html; charset=utf-8");
            mailSender.send(message);
    }

}