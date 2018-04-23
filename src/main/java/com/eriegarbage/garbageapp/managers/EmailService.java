package com.eriegarbage.garbageapp.managers;

import com.eriegarbage.garbageapp.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void sendReceipt(String email, Payment payment) throws MessagingException {
        String htmlMsg = "<html><body><h3>Here is your payment receipt:</h3>" + payment.generateReceipt() + "</body></html>";
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
        message.setContent(htmlMsg, "text/html");
        helper.setTo(email);
        helper.setSubject("Erie Garbage Receipt");
        emailSender.send(message);
    }
}