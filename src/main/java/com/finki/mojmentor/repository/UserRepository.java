package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findMentorByUsername(String username);
    /*Optional<Category> findMentorByCategory();*/
}
