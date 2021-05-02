package io.github.olgaak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "login_page";
    }

    @GetMapping("/profile")
    public String getProfilePage(){
        return "profile_page";
    }

}
