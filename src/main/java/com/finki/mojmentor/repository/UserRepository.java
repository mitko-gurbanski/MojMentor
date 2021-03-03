package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Mentor, String> {

    Optional<Mentor> findMentorByUsername(String username);
}
