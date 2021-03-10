package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.Model.MentorshipProgram;

import java.util.List;

public interface MentorService {
    public List<Mentor> listAllMentors();
    Mentor findMentorByUsername(String username);
    Category addCatgoryToMentorToMentorShipProgram(String category, String mentorshipProgram, String mentor);
    Mentor addMentorshipProgramToMentor(String mentorshipProgram, String username);
}
