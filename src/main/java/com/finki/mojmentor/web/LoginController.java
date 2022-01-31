package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.Model.exceptions.InvalidArgumentsException;
import com.finki.mojmentor.Model.exceptions.InvalidUserCredentialsException;
import com.finki.mojmentor.service.AuthService;
import com.finki.mojmentor.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService authService;
    private final AuthService authService1;

    public LoginController(UserService authService, AuthService authService1) {
        this.authService = authService;
        this.authService1 = authService1;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","login");
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.authService1.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
