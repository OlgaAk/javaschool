package io.github.olgaak.controller;

import io.github.olgaak.dto.TrainQueryDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getHomePage(ModelMap model){
        return "home_page";
    }

    //todo auto mapping to dto
    @GetMapping("/train-query")
    public String trainQuery(@RequestParam("departure_station") String departureStation,
                             @RequestParam("arrival_station") String arrivalStation,
                             @RequestParam("departure_date") String departureDate){
        TrainQueryDto trainQuery = new TrainQueryDto(departureStation,arrivalStation,departureDate);
        return "redirect:/";
    }

}
