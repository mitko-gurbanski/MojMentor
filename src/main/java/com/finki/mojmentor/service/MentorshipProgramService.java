package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.MentorshipProgram;

import java.util.List;

public interface MentorshipProgramService {
    MentorshipProgram findByProgramName(String name);
    MentorshipProgram findById(Long id);
    List<MentorshipProgram> findAll();
    MentorshipProgram save(MentorshipProgram mentorshipProgram);
    MentorshipProgram addStudentToMentorshipProgram(String username, Long programId);
    List<MentorshipProgram> findByUser(String username);
}
