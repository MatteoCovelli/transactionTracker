package com.example.transactionTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showMyLoginPage() {

        return "login-page";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {

        return "access-denied";
    }

}
