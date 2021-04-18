package io.github.olgaak.controller;

import io.github.olgaak.entity.Train;
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

    @GetMapping("/admin")
    public String getAdminPage(ModelMap model){
        model.addAttribute("name", "Tom");
        List<Train> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
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

}
