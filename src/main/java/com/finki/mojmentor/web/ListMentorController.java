package com.finki.mojmentor.web;

import com.finki.mojmentor.service.MentorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list-mentors")
public class ListMentorController {

    private final MentorService mentorService;

    public ListMentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping()
    public String getListMentors(Model model){
        model.addAttribute("mentors",mentorService.listAllMentors());
        return "list-mentors";
    }

}
