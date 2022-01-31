package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.EmailToken;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.repository.MentorRepository;
import com.finki.mojmentor.service.EmailTokenService;
import com.finki.mojmentor.service.MentorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
@RequestMapping()
public class ActivationController {

    private final MentorService mentorService;
    private final EmailTokenService emailTokenService;
    private final MentorRepository mentorRepository;
    public ActivationController(MentorService mentorService, EmailTokenService emailTokenService, MentorRepository mentorRepository) {
        this.mentorService = mentorService;
        this.emailTokenService = emailTokenService;
        this.mentorRepository = mentorRepository;
    }

    @GetMapping("/activation")
    public String checkActivationToken(@RequestParam("token") String token){
        EmailToken emailToken = emailTokenService.findByToken(token);
        if (emailTokenService.findByToken(token)!=null){
            User activatedUser = emailToken.getUser();
            activatedUser.setEnabled(true);
            mentorRepository.save(activatedUser);
            emailTokenService.clearToken(token);
            return "redirect:/login";
        }
        return "redirect:/login";
    }

    @GetMapping("/access-denied")
    public String activateAccountFirst(){
        return "activate-account";
    }

}
