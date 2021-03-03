package com.finki.mojmentor.web;

import com.finki.mojmentor.service.MentorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mentors")
public class MentorsController {

    private final MentorService mentorService;

    public MentorsController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping("/all-mentors")
    public String getListMentors(Model model){
        model.addAttribute("mentors",mentorService.listAllMentors());
        return "list-mentors";
    }
}
