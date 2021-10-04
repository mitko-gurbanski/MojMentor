package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorshipProgramRepository extends JpaRepository<MentorshipProgram,Long> {
    Optional<MentorshipProgram> findByProgramName(String name);
}
