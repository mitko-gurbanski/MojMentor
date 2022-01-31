package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    public void sendAccountActivationMail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplay@mojmentor.com");
        message.setTo(to);
        message.setSubject("[MojMentor] Activate your account");
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        baseUrl+="/activation?token="+token;
        message.setText("Activate your account here:"+ baseUrl);
        emailSender.send(message);
    }
}