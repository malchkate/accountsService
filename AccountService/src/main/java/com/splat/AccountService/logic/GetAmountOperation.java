package com.splat.AccountService.logic;

import com.splat.AccountService.service.DBAdapter;
import com.splat.AccountService.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetAmountOperation {

    private final DBAdapter dbAdapter;

    public Long getAmount(int id){
        Optional<Account> account = dbAdapter.getAccount(id) ;
        long amount = 0;
        if (account.isPresent()) {
            amount = account.get().getAmount();
        }
        return amount;
    }
}
