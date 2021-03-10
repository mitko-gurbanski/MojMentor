package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.Model.Mentor;
import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.repository.CategoryRepository;
import com.finki.mojmentor.repository.MentorRepository;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {
    private final UserService userService;
    private final MentorService mentorService;
    public HomePageController(UserService userService, MentorService mentorService) {
        this.userService = userService;
        this.mentorService = mentorService;
    }

    @GetMapping()
    public String gethomePage(){
        //userService.register("Mitko06","admin123","admin123","Mitko","Gurbanski",Role.MENTOR);
        mentorService.addCatgoryToMentorToMentorShipProgram("TestCategory1","TestMentorshipProgram1","Mitko06");
        return "homePage";
    }
}
