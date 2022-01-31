package com.finki.mojmentor.web;


import com.finki.mojmentor.Model.*;
import com.finki.mojmentor.Model.enumerations.MentorshipProgramLevel;
import com.finki.mojmentor.Model.enumerations.RoomStatus;
import com.finki.mojmentor.repository.RoomConferenceRepository;
import com.finki.mojmentor.service.CategoryService;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.MentorshipProgramService;
import com.finki.mojmentor.service.UserService;
import com.finki.mojmentor.util.FileUploadUtil;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mentor")
public class MentorController {
    private final UserService userService;
    private final MentorService mentorService;
    private final RoomConferenceRepository roomConferenceRepository;
    private final CategoryService categoryService;
    private final MentorshipProgramService mentorshipProgramService;

    public MentorController(UserService userService, MentorService mentorService, RoomConferenceRepository roomConferenceRepository, CategoryService categoryService, MentorshipProgramService mentorshipProgramService) {
        this.userService = userService;
        this.mentorService = mentorService;
        this.roomConferenceRepository = roomConferenceRepository;
        this.categoryService = categoryService;
        this.mentorshipProgramService = mentorshipProgramService;
    }

    @Transactional
    @GetMapping("/me")
    public String getMentorDashboardPage(HttpServletRequest request){

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        request.getSession().setAttribute("user", username);

        RoomConference roomConference = roomConferenceRepository.readRoomConferenceByUser1(mentorService.findMentorByUsername("Mitko06"));
        RoomConference newRoomConference = new RoomConference();
        newRoomConference.setUser1(mentorService.findMentorByUsername("Mitko08"));
        newRoomConference.setUser2(mentorService.findMentorByUsername("Mitko09"));
        newRoomConference.setRoomToken(new RandomString(100).nextString());

        roomConferenceRepository.save(newRoomConference);

        List<RoomConference> activeRoomConferences = roomConferenceRepository.readRoomConferencesByStatus(RoomStatus.ACTIVE);
        List<RoomConference> inactiveRoomConferences = roomConferenceRepository.readRoomConferencesByStatus(RoomStatus.INACTIVE);

        List<RoomConference> activeRoomConferencesWithMentor = roomConferenceRepository.readRoomConferenceByStatusAndUser1(RoomStatus.ACTIVE,mentorService.findMentorByUsername("Mitko06"));


        //mentorService.addCatgoryToMentorToMentorShipProgram("TestCategory1","TestMentorshipProgram1","Mitko06");
        return "/mentor/mentor-dashboard";
    }
    @GetMapping("/my-programs")
    public String getMentorMentorshipProgramsPage(Model model, HttpServletRequest request){

        RoomConference roomConference = roomConferenceRepository.readRoomConferenceByUser1(mentorService.findMentorByUsername("Mitko06"));
        RoomConference newRoomConference = new RoomConference();

        User me = mentorService.getLoggedInMentor(request);
        List<MentorshipProgram> programs = me.getMentorshipPrograms();
        model.addAttribute("programs",programs);

        /*newRoomConference.setUser1(mentorService.findMentorByUsername("Mitko08"));
        newRoomConference.setUser2(mentorService.findMentorByUsername("Mitko09"));
        newRoomConference.setRoomToken(new RandomString(100).nextString());

        roomConferenceRepository.save(newRoomConference);

        List<RoomConference> activeRoomConferences = roomConferenceRepository.readRoomConferencesByStatus(RoomStatus.ACTIVE);
        List<RoomConference> inactiveRoomConferences = roomConferenceRepository.readRoomConferencesByStatus(RoomStatus.INACTIVE);

        List<RoomConference> activeRoomConferencesWithMentor = roomConferenceRepository.readRoomConferenceByStatusAndUser1(RoomStatus.ACTIVE,mentorService.findMentorByUsername("Mitko06"));
        */

        //mentorService.addCatgoryToMentorToMentorShipProgram("TestCategory1","TestMentorshipProgram1","Mitko06");
        return "/mentor/mentor-mentorship-programs";
    }
    @GetMapping("/my-students")
    public String getMentorStudentsEnrolledInProgram(Model model, HttpServletRequest request){

        User me = mentorService.getLoggedInMentor(request);
        List<MentorshipProgram> programs = me.getMentorshipPrograms();
        List<User> students = new ArrayList<>();
        List<User> usersEnrolledInPrograms = new ArrayList<>();

        HashMap<User,HashMap<String,Object>> users = new HashMap<>();

        /*for (MentorshipProgram program: programs)
        {
            usersEnrolledInPrograms = program.getStudentsEnrolled();
            for (User user : usersEnrolledInPrograms)
            {
                if (users.containsKey(user)){
                    HashMap<String,Object> newHash = users.get(user);
                    int value = (int) users.get(user);
                    users.replace(user,++value);
                }
                else{
                    HashMap<String,Object> newHash=new HashMap<>();
                    newHash.put("")
                    users.put(user,1);
                }
            }
        }*/

        List<User> userList = new ArrayList<>();
        for (MentorshipProgram program: programs){
            usersEnrolledInPrograms = program.getStudentsEnrolled();
            for (User user: usersEnrolledInPrograms){
                if (!userList.contains(user)){
                    userList.add(user);
                }
            }
        }

        model.addAttribute("userList",userList);
        users = new HashMap<>();
        return "/mentor/mentor-my-students";
    }

    @GetMapping("/add-new-program")
    public String addNewProgramPage(Model model){
        List<MentorshipProgramLevel> programLevels =
                new ArrayList<MentorshipProgramLevel>(EnumSet.allOf(MentorshipProgramLevel.class));
        List<Category> allCategories = categoryService.listAll();
        model.addAttribute("levels",programLevels);
        model.addAttribute("categories",allCategories);

        return "/mentor/mentor-add-new-program";
    }

    @PostMapping("/add-new-program")
    public String saveNewProgram(@RequestParam String programName,
                                 @RequestParam String briefSDescription,
                                 @RequestParam Float price,
                                 @RequestParam String programLevel,
                                 @RequestParam String summary,
                                 @RequestParam String[] categories,
                                 @RequestParam(value = "image",required = false) MultipartFile multipartFile) throws IOException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();



        MentorshipProgram newMentorshipProgram = new MentorshipProgram();
        newMentorshipProgram.setProgramName(programName);
        newMentorshipProgram.setProgramDescription(briefSDescription);
        newMentorshipProgram.setLevel(MentorshipProgramLevel.valueOf(programLevel));
        newMentorshipProgram.setSummary(summary);

        List<Category> categoryList = new ArrayList<>();
        for (String cat : categories){
            categoryList.add(categoryService.findCategoryByCategoryName(cat));
        }

        newMentorshipProgram.setCategoryList(categoryList);
        newMentorshipProgram.author=mentorService.findMentorByUsername(currentPrincipalName);
        newMentorshipProgram.setPrice(price);






        mentorshipProgramService.save(newMentorshipProgram);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        MentorshipProgram tmpMentorshipProgram = mentorshipProgramService.findByProgramName(newMentorshipProgram.programName);
        tmpMentorshipProgram.setImg(fileName);
        String uploadDir = "mentorship-program-photos/" + tmpMentorshipProgram.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        mentorshipProgramService.save(tmpMentorshipProgram);

        mentorService.addMentorshipProgramToMentor(newMentorshipProgram.programName,currentPrincipalName);
        return "redirect:/mentor/my-programs?status=success";
    }

    @GetMapping("/mentorship-program/{id}/edit")
    public String editMentorshipProgram(@PathVariable Long id, Model model){
        MentorshipProgram program = mentorshipProgramService.findById(id);
        model.addAttribute("program",program);

        List<MentorshipProgramLevel> programLevels =
                new ArrayList<MentorshipProgramLevel>(EnumSet.allOf(MentorshipProgramLevel.class));
        List<Category> allCategories = categoryService.listAll();
        model.addAttribute("levels",programLevels);
        model.addAttribute("categories",allCategories);

        List<Category> programCategories = mentorshipProgramService.findById(id).getCategoryList();
        model.addAttribute("programCategories",programCategories);

        return "/mentor/mentor-edit-program";
    }
    @PostMapping("/mentorship-program/{id}/edit")
    public String saveProgram(@PathVariable Long id,
                              @RequestParam String programName,
                              @RequestParam String briefSDescription,
                              @RequestParam Float price,
                              @RequestParam String programLevel,
                              @RequestParam String summary,
                              @RequestParam String[] categories,
                              @RequestParam(value = "image",required = false) MultipartFile multipartFile,
                              Model model) throws IOException {


        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        MentorshipProgram mentorshipProgram = mentorshipProgramService.findById(id);

        mentorshipProgram.setImg(fileName);

        MentorshipProgram program = mentorshipProgramService.save(mentorshipProgram);

        String uploadDir = "mentorship-program-photos/" + program.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        MentorshipProgram newMentorshipProgram = mentorshipProgramService.findById(id);
        newMentorshipProgram.setProgramName(programName);
        newMentorshipProgram.setProgramDescription(briefSDescription);
        newMentorshipProgram.setPrice(price);
        newMentorshipProgram.setLevel(MentorshipProgramLevel.valueOf(programLevel));
        newMentorshipProgram.setSummary(summary);

        List<Category> categoryList = new ArrayList<>();
        for (String cat : categories){
            categoryList.add(categoryService.findCategoryByCategoryName(cat));
        }

        newMentorshipProgram.setCategoryList(categoryList);

        mentorshipProgramService.save(newMentorshipProgram);

        return "redirect:/mentorship-program/"+id+"?status=success";
    }


    @GetMapping()
    public String home(){
        /*userService.register("Mitko06","admin123","admin123","Mitko","Gurbanski", Role.MENTOR);
        userService.register("Mitko07","admin123","admin123","Mitko","Gurbanski", Role.MENTOR);
        userService.register("Mitko08","admin123","admin123","Mitko","Gurbanski", Role.MENTOR);
        userService.register("Mitko09","admin123","admin123","Mitko","Gurbanski", Role.MENTOR);
*/
        return "test";
    }

    @GetMapping("{username}")
    public String getMentorProfilePage(@PathVariable String username, Model model){
        User mentor = mentorService.findMentorByUsername(username);
        model.addAttribute("mentor", mentor);
        return "profile-mentor";
    }
    @GetMapping("my-reports")
    public String getReports(Model model, HttpServletRequest request){
        User mentor = mentorService.getLoggedInMentor(request);
        List<Purchase> purchaseList = mentor.getPurchaseList();

        List<User> allStudents=new ArrayList<>();
        List<MentorshipProgram> mentorshipProgramList=mentor.getMentorshipPrograms();
        for (MentorshipProgram program : mentorshipProgramList)
        {
            if (!allStudents.contains(program.author)){
                allStudents.add(program.author);
            }
        }

        long earnings = 0;
        for (Purchase purchase :
                purchaseList) {
            earnings+=purchase.getAmount();
        }

        model.addAttribute("earnings",earnings);
        model.addAttribute("students",allStudents.size());
        purchaseList.sort(Comparator.comparing(Purchase::getCreateDate));
        purchaseList.stream().limit(5);
        model.addAttribute("lastTransactions",purchaseList);

        //String dateStart = ((Timestamp) purchaseList.get(0).getCreateDate()).toLocalDateTime().toString()









        return"string";
    }


}
