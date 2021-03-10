package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.MentorshipProgram;

import java.util.List;

public interface MentorshipProgramService {
    MentorshipProgram findByProgramName(String name);
    List<MentorshipProgram> findAll();
}
