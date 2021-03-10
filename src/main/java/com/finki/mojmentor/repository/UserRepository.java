package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.Model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findMentorByUsername(String username);
    /*Optional<Category> findMentorByCategory();*/
}
