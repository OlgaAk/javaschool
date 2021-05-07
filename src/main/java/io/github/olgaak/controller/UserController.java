package io.github.olgaak.controller;

import io.github.olgaak.dto.UserDto;
import io.github.olgaak.entity.User;
import io.github.olgaak.exception.UserAlreadyExistException;
import io.github.olgaak.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup_page";
    }

    @GetMapping("/purchase/{routeId}")
    public String getPurchasePage(@PathVariable("routeId") long routeId, ModelMap model) {
        model.addAttribute("routeId", routeId);
        return "purchase_page";
    }

    @PostMapping("/login/processsignup")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto, ModelMap model) {
        try {
            User registered = userService.registerNewUserAccount(userDto);
            model.addAttribute("successRegister", registered);
        } catch (UserAlreadyExistException uaeEx) {
            model.addAttribute("errorMessage",
                    "An account for that username/email already exists.");
        }
        return "redirect:/user/profile";
    }

    @GetMapping("/profile")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        String userEmail = authentication.getName();
        UserDto userDto = userService.findByEmail(userEmail);
        model.addAttribute("user", userDto);
        return "profile_page";
    }

}
