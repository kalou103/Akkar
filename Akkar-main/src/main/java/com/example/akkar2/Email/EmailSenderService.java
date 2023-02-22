package com.example.akkar2.Email;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
    @AllArgsConstructor
    public class EmailSenderService {
        @Autowired
        private final JavaMailSender mailSender;

        public void sendEmail(String toEmail ,String subject,String body,String text)
        {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ilyes.nakhli@esprit.tn");
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            System.out.println("Mail sent");
        }




}
