package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.repository.MentorRepository;
import com.finki.mojmentor.service.MentorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;

    public MentorServiceImpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @Override
    public List<Mentor> listAllMentors() {
        return mentorRepository.listAllMentors();
    }

}
