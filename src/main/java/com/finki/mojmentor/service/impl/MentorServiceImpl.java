package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.exceptions.MentorNotFoundException;
import com.finki.mojmentor.repository.MentorRepository;
import com.finki.mojmentor.service.CategoryService;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.MentorshipProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final CategoryService categoryService;
    private final MentorshipProgramService mentorshipProgramService;

    public MentorServiceImpl(MentorRepository mentorRepository, CategoryService categoryService, MentorshipProgramService mentorshipProgramService) {
        this.mentorRepository = mentorRepository;
        this.categoryService = categoryService;
        this.mentorshipProgramService = mentorshipProgramService;
    }

    @Override
    public List<Mentor> listAllMentors() {
        return mentorRepository.findAll();
    }

    @Override
    public Mentor findMentorByUsername(String username) {
        return mentorRepository.findMentorByUsername(username).orElseThrow(()->new MentorNotFoundException(username));
    }

    @Override
    public Category addCatgoryToMentorToMentorShipProgram(String categoryName, String mentorshipProgramName, String username) {
        Mentor mentor = mentorRepository.findMentorByUsername(username).orElseThrow(()->new MentorNotFoundException(username));
        Category category = categoryService.findCategoryByCategoryName(categoryName);
        MentorshipProgram mentorshipProgram = mentorshipProgramService.findByProgramName(mentorshipProgramName);

        mentor.getMentorshipPrograms()
                .stream()
                .filter(r->r.id.equals(mentorshipProgram.id))
                .findFirst()
                .orElseThrow()
                .getCategoryList()
                .add(category);
        mentorRepository.save(mentor);
        return category;
    }

    @Override
    public Mentor addMentorshipProgramToMentor(String mentorshipProgram, String username) {
        Mentor mentor = mentorRepository.findMentorByUsername(username).orElseThrow(()->new MentorNotFoundException(username));
        return mentorRepository.save(mentor);
    }


}
