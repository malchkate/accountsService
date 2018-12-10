package com.splat.AccountService.logic;

import com.splat.AccountService.service.DBAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class IncrementAmountOperation {

    private final DBAdapter dbAdapter;

    public void incrementAmount(int id, long value){
        dbAdapter.createAccountIfAbsent(id);
        dbAdapter.incrementAmount(id, value);
    }
}
