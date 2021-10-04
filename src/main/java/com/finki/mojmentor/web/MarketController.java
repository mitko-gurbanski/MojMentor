package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.service.EmailService;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.MentorshipProgramService;
import com.finki.mojmentor.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/market")
public class MarketController{

    private final MentorshipProgramService mentorshipProgramService;
    private final EmailService emailService;
    private final UserService userService;
    private final MentorService mentorService;


    public MarketController(MentorshipProgramService mentorshipProgramService, EmailService emailService, UserService userService, MentorService mentorService) {
        this.mentorshipProgramService = mentorshipProgramService;
        this.emailService = emailService;
        this.userService = userService;
        this.mentorService = mentorService;
    }

    @GetMapping("/browse")
    public String browseMarketPage(Model model){
        //emailService.sendAccountActivationMail("gurbanskim@gmail.com","testtoken");
        //userService.register("Mitko11","admin123","admin123","gurbanskimitko@gmail.com","Mitko","Gurbanski", Role.MENTOR);
        List<MentorshipProgram> mentorshipProgramList = mentorshipProgramService.findAll();

        model.addAttribute("programs",mentorshipProgramList);


        return "student/browse-programs";
    }
    @GetMapping("/mentorship-program/{id}")
    public String getMentorshipProgramDetailsPage(@PathVariable Long id, Model model){
        MentorshipProgram mentorshipProgram = mentorshipProgramService.findById(id);
        model.addAttribute("program",mentorshipProgram);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User currentUser = mentorService.findMentorByUsername(currentPrincipalName);

        if (currentUser.getMentorshipPrograms().contains(mentorshipProgram)){
            model.addAttribute("programPurchesed",true);
        }
        else {
            model.addAttribute("programPurchesed",false);
        }

        return "/student/student-mentorship-program-preview";
    }
}
