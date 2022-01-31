package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.RoomConference;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.enumerations.RoomStatus;
import com.finki.mojmentor.repository.MentorshipProgramRepository;
import com.finki.mojmentor.repository.RoomConferenceRepository;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.MentorshipProgramService;
import com.finki.mojmentor.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final UserService userService;
    private final MentorService mentorService;
    private final RoomConferenceRepository roomConferenceRepository;
    private final MentorshipProgramRepository mentorshipProgramRepository;
    private final MentorshipProgramService mentorshipProgramService;

    public StudentController(UserService userService, MentorService mentorService, RoomConferenceRepository roomConferenceRepository, MentorshipProgramRepository mentorshipProgramRepository, MentorshipProgramService mentorshipProgramService) {
        this.userService = userService;
        this.mentorService = mentorService;
        this.roomConferenceRepository = roomConferenceRepository;
        this.mentorshipProgramRepository = mentorshipProgramRepository;
        this.mentorshipProgramService = mentorshipProgramService;
    }


    @GetMapping("/me")
    public String getStudentDashboardPage(){

        RoomConference roomConference = roomConferenceRepository.readRoomConferenceByUser1(mentorService.findMentorByUsername("Mitko06"));
        RoomConference newRoomConference = new RoomConference();
        mentorshipProgramService.findByUser("Mitko07");
        newRoomConference.setUser1(mentorService.findMentorByUsername("Mitko08"));
        newRoomConference.setUser2(mentorService.findMentorByUsername("Mitko09"));
        newRoomConference.setRoomToken(new RandomString(100).nextString());

        roomConferenceRepository.save(newRoomConference);

        List<RoomConference> activeRoomConferences = roomConferenceRepository.readRoomConferencesByStatus(RoomStatus.ACTIVE);
        List<RoomConference> inactiveRoomConferences = roomConferenceRepository.readRoomConferencesByStatus(RoomStatus.INACTIVE);

        List<RoomConference> activeRoomConferencesWithMentor = roomConferenceRepository.readRoomConferenceByStatusAndUser1(RoomStatus.ACTIVE,mentorService.findMentorByUsername("Mitko06"));


        //mentorService.addCatgoryToMentorToMentorShipProgram("TestCategory1","TestMentorshipProgram1","Mitko06");
        return "/student/student-dashboard";
    }
    @GetMapping("/my-programs")
    public String myProgramsPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();


        List<MentorshipProgram> mentorshipProgramList = mentorshipProgramService.findByUser(currentPrincipalName);
        model.addAttribute("programs",mentorshipProgramList);

        return "/student/student-my-programs";
    }

    @GetMapping("/my-favorite-programs")
    public String myFavoriteProgramsPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User currentUser = mentorService.findMentorByUsername(currentPrincipalName);

        model.addAttribute("programs",currentUser.getFavoriteProgramList());

        return "/student/student-my-favorite-programs";
    }

    @PostMapping("add-to-favorites")
    public String addToFavorites(@RequestParam Long id, HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User currentUser = mentorService.findMentorByUsername(currentPrincipalName);
        mentorService.addMentorshipProgramFavorites(currentUser.getId(),id);

        return "redirect:/student/my-favorite-programs";
    }
}
