package io.github.olgaak.controller;

import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.dto.UserDto;
import io.github.olgaak.entity.User;
import io.github.olgaak.exception.UserAlreadyExistException;
import io.github.olgaak.security.CustomUserDetails;
import io.github.olgaak.service.api.TicketService;
import io.github.olgaak.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public TicketService ticketService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @GetMapping("/login-error")
    public String getLoginErrorPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login_page";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup_page";
    }


    @PostMapping("/login/processsignup")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "signup_page";
        }
        try {
            User registered = userService.registerNewUserAccount(user);
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
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        long userId = details.getUserId();
        UserDto userDto = userService.findByEmail(userEmail);
        List<TicketDto> ticketDtos = ticketService.getUserTickets(userId);
        model.addAttribute("user", userDto);
        model.addAttribute("tickets", ticketDtos);
        return "profile_page";
    }

}
