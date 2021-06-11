package io.github.olgaak.controller;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.StationDto;
import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.*;
import io.github.olgaak.exception.ActionNotAllowedException;
import io.github.olgaak.service.MessageSender;
import io.github.olgaak.service.api.RouteService;
import io.github.olgaak.service.api.StationService;
import io.github.olgaak.service.api.TimetableService;
import io.github.olgaak.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public TrainService trainService;

    @Autowired
    public StationService stationService;

    @Autowired
    public TimetableService timetableService;

    @Autowired
    public RouteService routeService;

    @Autowired
    public MessageSender messageSender;


    @GetMapping("")
    public String getAdminPage(ModelMap model) {
        model.addAttribute("name", "Tom");
        List<TrainDto> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
        List<StationDto> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "admin_page";
    }

    @PostMapping("/add/train")
    public String addTrain(@ModelAttribute("train") Train train) {
        trainService.createNewTrain(train);
        messageSender.sendMessage();//todo aop
        return "redirect:/admin";
    }

    @GetMapping("/delete/train/{id}")
    public String deleteTrain(@PathVariable("id") long id) {
        trainService.deleteTrain(id);
        return "redirect:/admin";
    }

    @PostMapping("/edit/train")
    public String editTrain(@ModelAttribute("train") Train train) {
        trainService.editTrain(train);
        return "redirect:/admin";
    }

    @PostMapping("/add/station")
    public String addStation(@ModelAttribute("station") Station station) {
        stationService.createNewStation(station);
        return "redirect:/admin";
    }

    @PostMapping("/edit/station")
    public String editTrain(@ModelAttribute("station") Station station) {
        stationService.editStation(station);
        return "redirect:/admin";
    }

    @GetMapping("/delete/station/{id}")
    public String deleteStation(@PathVariable("id") long id) {
        stationService.deleteStation(id);
        return "redirect:/admin";
    }

    @PostMapping("/add/timeTableItem")
    public String addStation(@ModelAttribute("timetableItem") TimetableItem timetableItem) {
        timetableService.createNewTimetableItem(timetableItem);
        return "redirect:/admin";
    }

    @PostMapping(path = "/add/route")
    public String addRoute(@ModelAttribute("route") RouteDto route) {
        routeService.createNewRoute(route);
        return "redirect:/admin";
    }

    @PostMapping("/edit/route")
    public String editRoute(@ModelAttribute("route") RouteDto routeDto) {
        routeService.editRoute(routeDto);
        return "redirect:/admin";
    }

    @GetMapping("/delete/route/{id}")
    public String deleteRoute(@PathVariable("id") long id) throws ActionNotAllowedException {
        routeService.deleteRoute(id);
        return "redirect:/admin";
    }

    @GetMapping("/routes/{trainId}")
    @ResponseBody
    public List<RouteDto> getTrainRoutes(@PathVariable("trainId") Long trainId, ModelMap model) {
        List<RouteDto> routes = routeService.getTrainRoutes(trainId);
        return routes;
    }

    @GetMapping("/station/{stationId}")
    @ResponseBody
    public StationDto getTrainRoutes(@PathVariable("stationId") long stationId, ModelMap model) {
        StationDto station = stationService.getStationById(stationId);
        return station;
    }
}
