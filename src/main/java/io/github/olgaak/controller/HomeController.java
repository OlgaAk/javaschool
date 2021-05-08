package io.github.olgaak.controller;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TrainQueryDto;
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
        TrainQueryDto trainQuery = new TrainQueryDto();
        List<RouteDto> routes = new ArrayList<>();
        // todo get from frontend stations id instead of name string (substitute input with js + list)
        trainQuery.setDepartureDate("2021-05-10");
        trainQuery.setDepartureStationId(3);
        trainQuery.setArrivalStationId(1);
        routes = routeService.getTrainRoutesByQuery(trainQuery);
        model.addAttribute("routes", routes);
        return "home_page";
    }

}
