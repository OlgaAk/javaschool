package io.github.olgaak.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.olgaak.dto.DatePickerMaxMinDto;
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

    private static final Logger logger
            = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String getHomePage(ModelMap model) {
        List<StationDto> stationDtos = stationService.getAllStations();
        DatePickerMaxMinDto datePickerMaxMin = new DatePickerMaxMinDto();
        model.addAttribute("stations", stationDtos);
        model.addAttribute("datePickerMaxMin", datePickerMaxMin);
        logger.info("Example log from {}", HomeController.class);
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
