package io.github.olgaak.controller;

import io.github.olgaak.entity.Station;
import io.github.olgaak.entity.Train;
import io.github.olgaak.service.api.StationService;
import io.github.olgaak.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    public TrainService trainService;

    @Autowired
    public StationService stationService;

    @GetMapping("/admin")
    public String getAdminPage(ModelMap model){
        model.addAttribute("name", "Tom");
        List<Train> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
        List<Station> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "admin";
    }

    @PostMapping("/add/train")
    public String addTrain(@ModelAttribute("train")Train train){
        System.out.println(train.getNumber());
        trainService.createNewTrain(train);
        return "redirect:/admin";
    }

    @GetMapping("/delete/train/{id}")
    public String deleteTrain(@PathVariable("id") long id){
       trainService.deleteTrain(id);
       return "redirect:/admin";
    }

    @PostMapping("/edit/train")
    public String editTrain(@ModelAttribute("train")Train train){
        trainService.editTrain(train);
        return "redirect:/admin";
    }

    @PostMapping("/add/station")
    public String addStation(@ModelAttribute("station") Station station){
        stationService.createNewStation(station);
        return "redirect:/admin";
    }

    @GetMapping("/delete/station/{id}")
    public String deleteStation(@PathVariable("id") long id){
        stationService.deleteStation(id);
        return "redirect:/admin";
    }

}
