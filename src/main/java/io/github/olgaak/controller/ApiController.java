package io.github.olgaak.controller;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.StationDto;
import io.github.olgaak.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private StationService stationService;

    @GetMapping("/station/{stationId}")
    @ResponseBody
    public StationDto getStationTimetable(@PathVariable("stationId") long stationId) {
        StationDto station = stationService.getStationById(stationId);
        return station;
 }

}

