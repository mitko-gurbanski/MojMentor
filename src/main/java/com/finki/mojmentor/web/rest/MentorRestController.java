package com.finki.mojmentor.web.rest;

import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.service.MentorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mentor")
public class MentorRestController {
    private final MentorService mentorService;

    public MentorRestController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<User> listMentors(){
        return mentorService.listAllMentors();
    }
}
