package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.enumerations.Role;
import com.finki.mojmentor.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {
    private final UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String gethomePage(){

        userService.register("Mitko06","admin123","admin123","Mitko","Gurbanski", Role.MENTOR);
        return "homePage";
    }
}
