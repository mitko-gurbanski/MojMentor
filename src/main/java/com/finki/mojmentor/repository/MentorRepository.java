package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<User, Long> {
    Optional<User> findMentorByUsername(String username);
    List<User> findMentorsBySummaryContaining(String query);
    Optional<User> findById(Long id);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
