package com.splat.AccountService.controller;

import com.splat.AccountService.StatisticsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsProvider statisticsProvider;

    @GetMapping("stats/meangets")
    public String getMeanGetRequests(){
        return "Mean of GET requests is " + statisticsProvider.getRequests.getMeanRate();
    }

    @GetMapping("stats/allgets")
    public String getAllGetRequests(){
        return "Amount of all GET requests is " + statisticsProvider.getRequests.getCount();
    }

    @GetMapping("stats/meanposts")
    public String getMeanPostRequests(){
        return "Mean of POST requests is " + statisticsProvider.postRequests.getMeanRate();
    }

    @GetMapping("stats/allposts")
    public String getAllPostRequests(){
        return "Amount of all POST requests is " + statisticsProvider.postRequests.getCount();
    }

    @PostMapping("stats/nullgetstats")
    public void nullGetStatistics(){
        statisticsProvider.getRequests.mark(0);
    }

    @PostMapping("stats/nullpoststats")
    public void nullPostStatistics(){
        statisticsProvider.postRequests.mark(0);
    }


}
