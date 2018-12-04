package com.splat.AccountService.controller;

import com.splat.AccountService.StatisticsProvider;
import com.splat.AccountService.model.Account;
import com.splat.AccountService.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
public class AccountsController implements AccountService {

    private final AccountRepository accountRepository;
    private final StatisticsProvider statisticsProvider;

    @GetMapping("/{id}")
    @Cacheable(value = "accounts", key = "#id")
    @Override
    //@Transactional
    public Long getAmount(@PathVariable Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        long amount = 0;
        if (account.isPresent()) {
            amount = account.get().amount.get();
        }
        statisticsProvider.getRequests.mark();                      //todo
        return amount;
    }

    @PostMapping("/{id}/{value}")
    @Override
    @CacheEvict(value = "accounts", key = "#id", condition = "#value!=0")
    @Transactional
    public void addAmount(@PathVariable Integer id, @PathVariable Long value) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()){
            accountOptional.get().amount.addAndGet(value);
            accountRepository.save(accountOptional.get());
        } else{
            accountRepository.save(new Account(id, new AtomicLong(value)));
        }
        statisticsProvider.postRequests.mark();                      //todo
    }

}
