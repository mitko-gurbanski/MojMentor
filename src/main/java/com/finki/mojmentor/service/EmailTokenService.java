package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.EmailToken;

public interface EmailTokenService {
    EmailToken findByUserId(Long id);
    EmailToken save(Long userId);
    EmailToken findByToken(String token);
    void clearToken(String token);
}
