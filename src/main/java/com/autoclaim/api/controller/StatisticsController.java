package com.autoclaim.api.controller;

import com.autoclaim.api.model.response.StatisticsDetailsResponseModel;
import com.autoclaim.api.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stat")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;
    @GetMapping
    StatisticsDetailsResponseModel getStat() {
        return statisticsService.getStat();
    }
}
