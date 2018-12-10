package com.splat.AccountService.service;

import com.splat.AccountService.model.Account;
import com.splat.AccountService.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DBAdapter {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Optional<Account> getAccount(int id){
        return accountRepository.findById(id);
    }


    public void createAccountIfAbsent(int id){
        accountRepository.insertIfNotExist(id);
    }

    public void incrementAmount(int id, long value){
        accountRepository.incrementAmount(id, value);
    }

}
