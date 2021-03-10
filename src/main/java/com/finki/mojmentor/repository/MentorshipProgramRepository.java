package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.MentorshipProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorshipProgramRepository extends JpaRepository<MentorshipProgram,Long> {
    Optional<MentorshipProgram> findByProgramName(String name);
}
