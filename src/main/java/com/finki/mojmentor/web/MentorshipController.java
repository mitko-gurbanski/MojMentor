package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.repository.CategoryRepository;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.MentorshipProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/mentors")
public class MentorshipController {

    private final MentorService mentorService;
    private final CategoryRepository categoryRepository;
    private final MentorshipProgramService mentorshipProgramService;

    public MentorshipController(MentorService mentorService, CategoryRepository categoryRepository, MentorshipProgramService mentorshipProgramService) {
        this.mentorService = mentorService;
        this.categoryRepository = categoryRepository;
        this.mentorshipProgramService = mentorshipProgramService;
    }

    @GetMapping("{id}")
    public String getMentorshipProgram(@PathVariable Long id, Model model, HttpServletRequest request){

        MentorshipProgram mentorshipProgram = mentorService.getLoggedInMentor(request).getMentorshipPrograms().stream().filter(r->r.equals(mentorshipProgramService.findById(id))).findFirst().get();
        model.addAttribute("program",mentorshipProgram);
        return "/mentor/mentor-program-overview";
    }

    @GetMapping()
    public String browseMentors(Model model){
        List<User> filteredMentors = mentorService.findMentorsBySummaryContaining("1111");
        model.addAttribute("mentors", filteredMentors);

        return "browse-mentors";
    }

    @PostMapping()
    public String filterMentors(@RequestParam String query){

        List<User> filteredMentors = mentorService.findMentorsBySummaryContaining(query);

        return "browse-mentors";
    }

    @GetMapping("/all")
    public String getListMentors(Model model){
        model.addAttribute("mentors",mentorService.listAllMentors());
        //List<Category> categories = categoryRepository.find(mentorService.findMentorByUsername("Mitko06"));

        return "list-mentors";
    }
}
