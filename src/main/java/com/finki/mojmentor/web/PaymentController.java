package com.finki.mojmentor.web;

import com.finki.mojmentor.Model.MentorshipProgram;
import com.finki.mojmentor.Model.Purchase;
import com.finki.mojmentor.Model.User;
import com.finki.mojmentor.commons.Response;
import com.finki.mojmentor.service.MentorService;
import com.finki.mojmentor.service.MentorshipProgramService;
import com.finki.mojmentor.service.PurchaseService;
import com.finki.mojmentor.service.StripeService;
import com.stripe.model.Coupon;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {

    private String API_PUBLIC_KEY = "pk_test_FkbTcxrwYdxTGoTaLs8Vkb8P";

    private StripeService stripeService;
    private final MentorshipProgramService mentorshipProgramService;
    private final MentorService mentorService;
    private final PurchaseService purchaseService;

    public PaymentController(StripeService stripeService, MentorshipProgramService mentorshipProgramService, MentorService mentorService, PurchaseService purchaseService) {
        this.stripeService = stripeService;
        this.mentorshipProgramService = mentorshipProgramService;
        this.mentorService = mentorService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/subscription")
    public String subscriptionPage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "subscription";
    }

    @GetMapping("/charge")
    public String chargePage(Model model,
                             @RequestParam Long programId,
                             @RequestParam Long mentorId,
                             HttpServletRequest request) {
        request.getSession().setAttribute("mentorId",mentorId);
        request.getSession().setAttribute("programId",programId);
        MentorshipProgram program = mentorshipProgramService.findById(programId);
        model.addAttribute("program",program);
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "/student/student-payment";
    }

    /*========== REST APIs for Handling Payments ===================*/

    @PostMapping("/create-subscription")
    public @ResponseBody
    Response createSubscription(String email, String token, String plan, String coupon) {
        //validate data
        if (token == null || plan.isEmpty()) {
            return new Response(false, "Stripe payment token is missing. Please, try again later.");
        }

        //create customer first
        String customerId = stripeService.createCustomer(email, token);

        if (customerId == null) {
            return new Response(false, "An error occurred while trying to create a customer.");
        }

        //create subscription
        String subscriptionId = stripeService.createSubscription(customerId, plan, coupon);
        if (subscriptionId == null) {
            return new Response(false, "An error occurred while trying to create a subscription.");
        }

        // Ideally you should store customerId and subscriptionId along with customer object here.
        // These values are required to update or cancel the subscription at later stage.

        return new Response(true, "Success! Your subscription id is " + subscriptionId);
    }

    @PostMapping("/cancel-subscription")
    public @ResponseBody
    Response cancelSubscription(String subscriptionId) {
        boolean status = stripeService.cancelSubscription(subscriptionId);
        if (!status) {
            return new Response(false, "Failed to cancel the subscription. Please, try later.");
        }
        return new Response(true, "Subscription cancelled successfully.");
    }

    @PostMapping("/coupon-validator")
    public @ResponseBody
    Response couponValidator(String code) {
        Coupon coupon = stripeService.retrieveCoupon(code);
        if (coupon != null && coupon.getValid()) {
            String details = (coupon.getPercentOff() == null ? "$" + (coupon.getAmountOff() / 100) : coupon.getPercentOff() + "%") +
                    " OFF " + coupon.getDuration();
            return new Response(true, details);
        } else {
            return new Response(false, "This coupon code is not available. This may be because it has expired or has " +
                    "already been applied to your account.");
        }
    }

    @PostMapping("/create-charge")
    public @ResponseBody
    Response createCharge(String email, String token, Long mentorId, Long programId, HttpServletRequest request) {

        //validate data
        if (token == null) {
            return new Response(false, "Stripe payment token is missing. Please, try again later.");
        }
        float amount = mentorshipProgramService.findById((Long) request.getSession().getAttribute("programId")).price*100;
        //create charge
        String chargeId = stripeService.createCharge(email, token, (int) amount); //$9.99 USD
        if (chargeId == null) {
            return new Response(false, "An error occurred while trying to create a charge.");
        }

        //Get The mentorship program
        MentorshipProgram program = mentorshipProgramService.findById((Long) request.getSession().getAttribute("programId"));

        //Get current logged user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Long currentUserID = ((User) ((UsernamePasswordAuthenticationToken) authentication).getPrincipal()).getId();
        //Get author of the program
        User mentor = program.author;
        User buyer= mentorService.getUserById(currentUserID);

        mentorService.addMentorshipProgramToMentor(program.programName,currentPrincipalName);
        mentorshipProgramService.addStudentToMentorshipProgram(currentPrincipalName,program.id);


        // You may want to store charge id along with order information

        Purchase purchase = new Purchase();
        purchase.setAmount((long) amount);
        purchase.setTransacationID(chargeId);
        purchase.setBuyer(buyer);
        purchase.setMentorshipProgram(program);

        purchaseService.savePurchase(purchase);
        purchaseService.addPurchaseToMentor(purchase,mentor);



        return new Response(true, "Success! Your charge id is " + chargeId);
    }
}
