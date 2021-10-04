package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.EmailToken;
import com.finki.mojmentor.Model.exceptions.TokenNotFoundException;
import com.finki.mojmentor.repository.EmailTokenRepository;
import com.finki.mojmentor.service.EmailTokenService;
import com.finki.mojmentor.service.MentorService;
import org.springframework.stereotype.Service;

@Service
public class EmailTokenServiceImpl implements EmailTokenService {
    private final EmailTokenRepository emailTokenRepository;
    private final MentorService mentorService;

    public EmailTokenServiceImpl(EmailTokenRepository emailTokenRepository, MentorService mentorService) {
        this.emailTokenRepository = emailTokenRepository;
        this.mentorService = mentorService;
    }

    @Override
    public EmailToken findByUserId(Long id) {
        return emailTokenRepository.findByUser(mentorService.getUserById(id)).orElseThrow(()->new TokenNotFoundException());
    }

    @Override
    public EmailToken save(Long userId) {
        EmailToken token = new EmailToken();
        token.setUser(mentorService.getUserById(userId));
        return emailTokenRepository.save(token);
    }

    @Override
    public EmailToken findByToken(String token) {
        return emailTokenRepository.findEmailTokenByToken(token).orElseThrow(()->new TokenNotFoundException());
    }

    @Override
    public void clearToken(String token) {
        EmailToken emailToken = emailTokenRepository.findEmailTokenByToken(token).orElseThrow(()->new TokenNotFoundException());
        emailTokenRepository.delete(emailToken);
    }
}
