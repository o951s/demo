package com.example.demo.controller;

import com.example.demo.appuser.AppUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostLoginController {

    @GetMapping("/post-login")
    public String postLogin(Model model, @AuthenticationPrincipal AppUser appUser) {
        if (appUser == null) {
            return "redirect:/login?error"; // Redirect to login if the user is not authenticated
        }
        model.addAttribute("user", appUser);
        return "postLoginPage"; // Name of the HTML template
    }
}
