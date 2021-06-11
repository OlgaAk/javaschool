package io.github.olgaak.controller;

import io.github.olgaak.dto.RouteDto;
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

    @GetMapping("/station/{stationId}/{date}")
    @ResponseBody
    public List<String> getStationTimetable(@PathVariable("stationId") long stationId, @PathVariable("date") String date) {
        Random random = new Random();
        return Arrays.asList("foo"+random.nextInt(), "bar"+random.nextInt());
    }

}

