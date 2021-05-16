package io.github.olgaak.controller;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.security.CustomUserDetails;
import io.github.olgaak.service.api.RouteService;
import io.github.olgaak.service.api.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private RouteService routeService;

    @GetMapping("/purchase/{routeId}")
    public String getPurchasePage(@PathVariable("routeId") long routeId, ModelMap model) {
        RouteDto routeDto = routeService.getRouteById(routeId);
        model.addAttribute("route", routeDto);
        return "purchase_page";
    }

    @PostMapping("/purchase")
    public String makePurchase(@RequestBody TicketDto ticketDto, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() == "anonymousUser") {
            return "redirect:/user/login-error";
        }
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        long userId = details.getUserId();
        ticketDto.getPassenger().setUserId(userId);
        TicketDto ticketBought = ticketService.buyTicket(ticketDto);
        return "redirect:/user/profile";
    }
}
