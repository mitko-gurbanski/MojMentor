package com.finki.mojmentor.service;

public interface EmailService {
    void sendAccountActivationMail(String to, String token);
}
