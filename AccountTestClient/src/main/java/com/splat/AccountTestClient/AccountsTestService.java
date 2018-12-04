package com.splat.AccountTestClient;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AccountsTestService {

    private static final Logger logger = LoggerFactory.getLogger(AccountsTestService.class);

    private final RestTemplate restTemplate;

    @Async
    public CompletableFuture<Long> getAccountAmount(String url){
        logger.info("Getting " + url);
        Long accountAmount = restTemplate.getForObject(url, Long.class);
        logger.info("Resulting for url " + url + " .....      " + accountAmount);
        return CompletableFuture.completedFuture(accountAmount);
    }

    @Async
    public void postAccountAmount(String url){
        logger.info("Posting " + url);
        String body = "";
        restTemplate.postForObject(url, body, String.class);
    }
}
