package com.splat.AccountService.controller;

import com.splat.AccountService.service.StatisticsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsProvider statisticsProvider;

    @PostMapping("stats/dropstats")
    public void dropStatistics(){
        statisticsProvider.dropMetrics();
    }

}
