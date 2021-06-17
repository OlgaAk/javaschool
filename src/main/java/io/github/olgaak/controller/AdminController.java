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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private static final Logger logger
            = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("")
    public String getAdminPage() {
        return "train_page";
    }

    @GetMapping("/train")
    public String getTrainPage(ModelMap model) {
        List<TrainDto> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
        List<StationDto> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "train_page";
    }

    @GetMapping("/station")
    public String getStationPage(ModelMap model) {
        List<StationDto> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "station_page";
    }

    @PostMapping("/add/train")
    public String addTrain(@ModelAttribute("train") TrainDto trainDto) {
        logger.info("Train creation query received");
        trainService.createNewTrain(trainDto);
        logger.info("New train with routeplan and routes created");
        return "redirect:/admin/train";
    }

    @GetMapping("/delete/train/{id}")
    public String deleteTrain(@PathVariable("id") long id,  ModelMap model ) {
        try {
            trainService.deleteTrain(id);
        } catch (ActionNotAllowedException e) {
            String adminPageError = "train has tickets";
            model.addAttribute("adminPageError", adminPageError );
            return "redirect:/admin/train";
        }
        messageSender.sendMessage();
        return "redirect:/admin/train";
    }


    @PostMapping("/edit/train")
    public String editTrain(@ModelAttribute("train") Train train) {
        trainService.editTrain(train);
        messageSender.sendMessage();
        return "redirect:/admin/train";
    }

    @GetMapping("/train/{trainId}")
    public String getPurchasePage(@PathVariable("trainId") long trainId, ModelMap model) {
        TrainDto trainDto = trainService.getTrainById(trainId);
        model.addAttribute("train", trainDto);
        return "admin_page_train_info";
    }


    @PostMapping("/add/station")
    public String addStation(@ModelAttribute("station") Station station) {
        stationService.createNewStation(station);
        return "redirect:/admin/station";
    }

    @PostMapping("/edit/station")
    public String editTrain(@ModelAttribute("station") Station station) {
        stationService.editStation(station);
        return "redirect:/admin/station";
    }

    @GetMapping("/delete/station/{id}")
    public String deleteStation(@PathVariable("id") long id) {
        stationService.deleteStation(id);
        return "redirect:/admin/station";
    }

    @PostMapping("/add/timeTableItem")
    public String addStation(@ModelAttribute("timetableItem") TimetableItem timetableItem) {
        timetableService.createNewTimetableItem(timetableItem);
        return "redirect:/admin/train";
    }

//    @PostMapping(path = "/add/route")
//    public String addRoute(@ModelAttribute("route") RouteDto route) {
//        routeService.createNewRoute(route);
//        return "redirect:/admin";
//    }

    @PostMapping("/edit/route")
    public String editRoute(@ModelAttribute("route") RouteDto routeDto) {
        routeService.editRoute(routeDto);
        return "redirect:/admin";
    }

    @GetMapping("/delete/route/{id}")
    public String deleteRoute(@PathVariable("id") long id) throws ActionNotAllowedException {
        routeService.deleteRoute(id);
        return "redirect:/admin/train";
    }

    @GetMapping("/routes/{trainId}")
    @ResponseBody
    public List<RouteDto> getTrainRoutes(@PathVariable("trainId") Long trainId) {
        List<RouteDto> routes = routeService.getTrainRoutes(trainId);
        return routes;
    }

    @GetMapping("/station/{stationId}")
    @ResponseBody
    public StationDto getTrainRoutes(@PathVariable("stationId") long stationId) {
        StationDto station = stationService.getStationById(stationId);
        return station;
    }
}
