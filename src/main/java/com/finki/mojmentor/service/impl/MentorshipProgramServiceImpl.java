package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.exceptions.MentorNotFoundException;
import com.finki.mojmentor.Model.exceptions.MentorshipProgramNotFoundException;
import com.finki.mojmentor.repository.MentorRepository;
import com.finki.mojmentor.repository.MentorshipProgramRepository;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.MentorshipProgramService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorshipProgramServiceImpl implements MentorshipProgramService{

    private final MentorshipProgramRepository mentorshipProgramRepository;
    private final MentorService mentorService;
    private final MentorRepository mentorRepository;

    public MentorshipProgramServiceImpl(MentorshipProgramRepository mentorshipProgramRepository, MentorRepository mentorRepository, MentorService mentorService, MentorRepository mentorRepository1) {
        this.mentorshipProgramRepository = mentorshipProgramRepository;
        this.mentorService = mentorService;
        this.mentorRepository = mentorRepository1;
    }

    @Override
    public MentorshipProgram findByProgramName(String name) {
        return mentorshipProgramRepository.findByProgramName(name).orElseThrow(()->new MentorshipProgramNotFoundException());
    }

    @Override
    public MentorshipProgram findById(Long id) {
        return mentorshipProgramRepository.findById(id).orElseThrow(()->new MentorshipProgramNotFoundException());
    }

    @Override
    public List<MentorshipProgram> findAll() {
        return mentorshipProgramRepository.findAll();
    }

    @Override
    public MentorshipProgram save(MentorshipProgram mentorshipProgram) {
        return mentorshipProgramRepository.save(mentorshipProgram);
    }
    @Override
    public MentorshipProgram addStudentToMentorshipProgram(String username, Long programId){
        MentorshipProgram mentorshipProgram = mentorshipProgramRepository.findById(programId).orElseThrow(()-> new MentorshipProgramNotFoundException());
        List<User> studentsEnrolled = mentorshipProgram.getStudentsEnrolled();
        User student = mentorRepository.findMentorByUsername(username).orElseThrow(()-> new MentorNotFoundException(username));
        if (!studentsEnrolled.contains(student)){
            studentsEnrolled.add(student);
            mentorshipProgram.setStudentsEnrolled(studentsEnrolled);
        }
        return mentorshipProgramRepository.save(mentorshipProgram);
    }

    @Override
    public List<MentorshipProgram> findByUser(String username) {
        List<MentorshipProgram> mentorshipProgramList = mentorshipProgramRepository.findAll();
        List<MentorshipProgram> newList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        if (!mentorshipProgramList.isEmpty())
        {
            for (MentorshipProgram program : mentorshipProgramList){
                userList=program.getStudentsEnrolled();
                for (User user : program.getStudentsEnrolled()){
                    if (user.getUsername().equals(username))
                    {
                        newList.add(program);
                    }
                }
            }
        }
        return newList;

    }
}
