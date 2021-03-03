package com.finki.mojmentor.web.rest;

import com.finki.mojmentor.Model.Mentor;
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
    public List<Mentor> listMentors(){
        return mentorService.listAllMentors();
    }
}
