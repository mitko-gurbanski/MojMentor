package com.finki.mojmentor.service.impl;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.Purchase;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.exceptions.MentorNotFoundException;
import com.finki.mojmentor.Model.exceptions.MentorshipProgramNotFoundException;
import com.finki.mojmentor.Model.exceptions.UserIdNotFoundException;
import com.finki.mojmentor.repository.MentorRepository;
import com.finki.mojmentor.repository.MentorshipProgramRepository;
import com.finki.mojmentor.service.CategoryService;
import com.finki.mojmentor.service.MentorService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final CategoryService categoryService;
    private final MentorshipProgramRepository mentorshipProgramRepository;

    public MentorServiceImpl(MentorRepository mentorRepository, CategoryService categoryService, MentorshipProgramRepository mentorshipProgramRepository) {
        this.mentorRepository = mentorRepository;
        this.categoryService = categoryService;
        this.mentorshipProgramRepository = mentorshipProgramRepository;
    }

    @Override
    public List<User> listAllMentors() {
        return mentorRepository.findAll();
    }

    @Override
    public User findMentorByUsername(String username) {
        return mentorRepository.findMentorByUsername(username).orElseThrow(()->new MentorNotFoundException(username));
    }

    @Override
    public Category addCatgoryToMentorToMentorShipProgram(String categoryName, String mentorshipProgramName, String username) {
        User mentor = mentorRepository.findMentorByUsername(username).orElseThrow(()->new MentorNotFoundException(username));
        Category category = categoryService.findCategoryByCategoryName(categoryName);
        MentorshipProgram mentorshipProgram = mentorshipProgramRepository.findByProgramName(mentorshipProgramName).orElseThrow(()->new MentorshipProgramNotFoundException());

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
    public User addMentorshipProgramToMentor(String mentorshipProgram, String username) {
        User mentor = mentorRepository.findMentorByUsername(username).orElseThrow(()->new MentorNotFoundException(username));
        MentorshipProgram newMentorshipProgram = mentorshipProgramRepository.findByProgramName(mentorshipProgram).orElseThrow(()->new MentorshipProgramNotFoundException());
        mentor.getMentorshipPrograms().add(newMentorshipProgram);
        return mentorRepository.save(mentor);
    }

    @Override
    public User getLoggedInMentor(HttpServletRequest request) {
        String loggedInMentorName = request.getSession().getAttribute("user").toString();
        return mentorRepository.findMentorByUsername(loggedInMentorName).orElseThrow(()->new MentorNotFoundException(loggedInMentorName));

    }

    @Override
    public List<User> findMentorsBySummaryContaining(String query) {
        return mentorRepository.findMentorsBySummaryContaining(query);
    }

    @Override
    public User getUserById(Long userId) {
        return mentorRepository.findById(userId).orElseThrow(()-> new UserIdNotFoundException(userId));
    }

    @Override
    public User addMentorshipProgramFavorites(Long userId, Long programId) {
        User user = mentorRepository.findById(userId).orElseThrow(()->new MentorNotFoundException("userId"));
        MentorshipProgram mentorshipProgram = mentorshipProgramRepository.findById(programId).orElseThrow(()->new MentorshipProgramNotFoundException());

        user.getFavoriteProgramList().add(mentorshipProgram);

        return mentorRepository.save(user);
    }

    @Override
    public User addPurchaseToUser(Purchase purchase, User buyer) {
        User user = mentorRepository.findById(buyer.getId()).orElseThrow(()->new MentorNotFoundException(buyer.getId().toString()));
        user.getPurchaseList().add(purchase);
        return mentorRepository.save(user);
    }

}
