package com.example.akkar2.controllers;

import com.example.akkar2.services.SalesStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SalesStatisticsController {

    @Autowired
    private SalesStatisticsService salesStatisticsService;

    @GetMapping("/sales-statistics")
    public Map<String, Double> getSalesStatisticsWithPercentage() {
        return salesStatisticsService.getSalesStatisticsWithPercentage();

    }
}

