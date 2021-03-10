package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.exceptions.MentorshipProgramNotFoundException;
import com.finki.mojmentor.repository.MentorshipProgramRepository;
import com.finki.mojmentor.service.MentorshipProgramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorshipProgramServiceImpl implements MentorshipProgramService{

    private final MentorshipProgramRepository mentorshipProgramRepository;

    public MentorshipProgramServiceImpl(MentorshipProgramRepository mentorshipProgramRepository) {
        this.mentorshipProgramRepository = mentorshipProgramRepository;
    }

    @Override
    public MentorshipProgram findByProgramName(String name) {
        return mentorshipProgramRepository.findByProgramName(name).orElseThrow(()->new MentorshipProgramNotFoundException(name));
    }

    @Override
    public List<MentorshipProgram> findAll() {
        return mentorshipProgramRepository.findAll();
    }
}
