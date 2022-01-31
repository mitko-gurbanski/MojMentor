package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.exceptions.MentorNotFoundException;
import com.finki.mojmentor.service.MentorshipProgramService;
import com.finki.mojmentor.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping(value = "/testing")
public class TestingController {

    private final MentorshipProgramService mentorshipProgramService;

    public TestingController(MentorshipProgramService mentorshipProgramService) {
        this.mentorshipProgramService = mentorshipProgramService;
    }


    @GetMapping
    public String testingPage(Model model){
        MentorshipProgram mentorshipProgram = mentorshipProgramService.findById(6L);
        model.addAttribute("program",mentorshipProgram);
        return "testing";
    }
    @PostMapping
    public RedirectView saveUser(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        MentorshipProgram mentorshipProgram = mentorshipProgramService.findById(6L);

        mentorshipProgram.setImg(fileName);

        MentorshipProgram program = mentorshipProgramService.save(mentorshipProgram);

        String uploadDir = "mentorship-program-photos/" + program.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/testing", true);
    }
}
