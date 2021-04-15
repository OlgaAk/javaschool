package io.github.olgaak.controller;

import io.github.olgaak.entity.Train;
import io.github.olgaak.service.impl.TrainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    public TrainServiceImpl trainService;

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin";
    }

    @PostMapping("/add-train")
    public String addTrain(@ModelAttribute("train")Train train){
        System.out.println(train.getNumber());
        return "redirect:/admin";
    }

}
