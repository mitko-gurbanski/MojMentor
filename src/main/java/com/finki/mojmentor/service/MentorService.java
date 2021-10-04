package com.finki.mojmentor.service;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.Model.Purchase;
import com.finki.mojmentor.Model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MentorService {
    public List<com.finki.mojmentor.Model.User> listAllMentors();
    User findMentorByUsername(String username);
    Category addCatgoryToMentorToMentorShipProgram(String category, String mentorshipProgram, String mentor);
    User addMentorshipProgramToMentor(String mentorshipProgram, String username);
    User getLoggedInMentor(HttpServletRequest request);
    List<User> findMentorsBySummaryContaining(String query);
    User getUserById(Long user);
    User addMentorshipProgramFavorites(Long userId, Long programId);

    User addPurchaseToUser(Purchase purchase, User buyer);
}
