package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.repository.CategoryRepository;
import com.finki.mojmentor.service.MentorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mentors")
public class MentorController {

    private final MentorService mentorService;
    private final CategoryRepository categoryRepository;

    public MentorController(MentorService mentorService, CategoryRepository categoryRepository) {
        this.mentorService = mentorService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public String getListMentors(Model model){
        model.addAttribute("mentors",mentorService.listAllMentors());
        //List<Category> categories = categoryRepository.find(mentorService.findMentorByUsername("Mitko06"));

        return "list-mentors";
    }

    @GetMapping("{username}")
    public String getMentorProfilePage(@PathVariable String username, Model model){
        Mentor mentor = mentorService.findMentorByUsername(username);
        model.addAttribute("mentor",mentor);
        return "profile-mentor";
    }
}
