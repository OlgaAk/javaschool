package io.github.olgaak.controller;

import io.github.olgaak.dto.UserDto;
import io.github.olgaak.entity.User;
import io.github.olgaak.exceptions.UserAlreadyExistException;
import io.github.olgaak.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login_page";
    }

    @PostMapping("/login/signup")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto, ModelMap model) {
        try {
            User registered = userService.registerNewUserAccount(userDto);
            model.addAttribute("successRegister", registered);
        } catch (UserAlreadyExistException uaeEx) {
            model.addAttribute("message",
                    "An account for that username/email already exists.");
        }
        return "redirect:/admin";
    }

    @GetMapping("/profile")
    public String getProfilePage(){
        return "profile_page";
    }

}
