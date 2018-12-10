package com.splat.AccountService.controller;

import com.splat.AccountService.logic.GetAmountOperation;
import com.splat.AccountService.logic.IncrementAmountOperation;
import com.splat.AccountService.StatisticsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@EnableCaching
public class AccountsController implements AccountService {

    private final StatisticsProvider statisticsProvider;
    private final GetAmountOperation getAmountOperation;
    private final IncrementAmountOperation incrementAmountOperation;

    @GetMapping("/{id}")
    @Cacheable(value = "accounts", key = "#id")
    @Override
    public Long getAmount(@PathVariable Integer id) {
        statisticsProvider.getRequests.mark();
        return getAmountOperation.getAmount(id);
    }

    @PostMapping("/{id}/{value}")
    @CacheEvict(value = "accounts", key = "#id", condition = "#value!=0")
    @Override
    public void addAmount(@PathVariable Integer id, @PathVariable Long value) {
        incrementAmountOperation.incrementAmount(id, value);
        statisticsProvider.postRequests.mark();
    }

}
