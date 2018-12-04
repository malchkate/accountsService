package com.splat.AccountTestClient;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class ClientRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ClientRunner.class);

    private final AccountsTestService accountsTestService;
    private final ConfigurationsProcessor configurationsProcessor;

    @Override
    public void run(String... args) throws Exception{

        String url = configurationsProcessor.getBasicUrlPart();
        int randomId = 0;
        long randomAmountInc = 0;
        for (int r = 0; r < 3; r++) {//configurationsProcessor.getRCount()
            randomId = ThreadLocalRandom.current().nextInt(configurationsProcessor.getIdListL(), configurationsProcessor.getIdListR() + 1);

            accountsTestService.getAccountAmount(url + 1); //randomId);
        }
        for (int w = 0; w < 3; w++) { //configurationsProcessor.getWCount()
            randomId = ThreadLocalRandom.current().nextInt(configurationsProcessor.getIdListL(), configurationsProcessor.getIdListR() + 1);
            randomAmountInc = ThreadLocalRandom.current().nextLong(-1848, 180479);
            accountsTestService.postAccountAmount(url + 1 +"/" + 1);//randomId + "/" + randomAmountInc);
        }
        logger.info("Results");
        accountsTestService.getAccountAmount(url + 1);
    }
}
