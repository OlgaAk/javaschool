package io.github.olgaak.controller;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.StationDto;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.service.api.RouteService;
import io.github.olgaak.service.api.StationService;
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

    @Autowired
    public StationService stationService;

    @GetMapping("/")
    public String getHomePage(ModelMap model) {
        List<StationDto> stationDtos = stationService.getAllStations();
        model.addAttribute("stations", stationDtos);
        return "home_page";
    }

    //todo auto mapping to dto
    @GetMapping("/train-query")
    @ResponseBody
    public  List<RouteDto> trainQuery(@RequestParam("departure_station") String departureStation,
                             @RequestParam("arrival_station") String arrivalStation,
                             @RequestParam("departure_date") String departureDate,
                             ModelMap model) {
        TrainQueryDto trainQuery = new TrainQueryDto();
        List<RouteDto> routes = new ArrayList<>();
        trainQuery.setDepartureDate(departureDate);
        trainQuery.setDepartureStationId(departureStation);
        trainQuery.setArrivalStationId(arrivalStation);
        routes = routeService.getTrainRoutesByQuery(trainQuery);
        return routes;
    }

}
