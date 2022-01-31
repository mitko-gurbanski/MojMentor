package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.EmailToken;
import com.finki.mojmentor.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {
    Optional<EmailToken> findByUser(User user);
    Optional<EmailToken> findEmailTokenByToken(String token);
}
