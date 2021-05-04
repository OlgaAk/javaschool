package io.github.olgaak.controller;

import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.service.api.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    public RouteService routeService;

    @GetMapping("/")
    public String getHomePage(ModelMap model){
        return "home_page";
    }

    //todo auto mapping to dto
    @GetMapping("/train-query")
    public String trainQuery(@RequestParam("departure_station") String departureStation,
                             @RequestParam("arrival_station") String arrivalStation,
                             @RequestParam("departure_date") String departureDate,
                                   ModelMap model){
        TrainQueryDto trainQuery = new TrainQueryDto(departureStation,arrivalStation,departureDate);
        List<Route> routes = new ArrayList<>();
        routes = routeService.getTrainRoutesByQuery(trainQuery);
        model.addAttribute("routes", routes);
        return "home_page";
    }

}
