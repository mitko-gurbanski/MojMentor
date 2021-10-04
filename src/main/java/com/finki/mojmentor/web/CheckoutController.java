package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.ChargeRequest;
import com.finki.mojmentor.Model.enumerations.Currency;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.MentorshipProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Autowired
    private MentorshipProgramService mentorshipProgramService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", Currency.EUR);
        model.addAttribute("program",mentorshipProgramService.findById(2L));
        return "checkout";
    }
}