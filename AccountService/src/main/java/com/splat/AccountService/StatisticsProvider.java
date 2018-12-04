package com.splat.AccountService;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class StatisticsProvider {

    private static final MetricRegistry metrics = new MetricRegistry();
    public final Meter getRequests;
    public final Meter postRequests;
    final Slf4jReporter reporter;

    StatisticsProvider(){
        getRequests = metrics.meter("getRequests");
        postRequests = metrics.meter("postRequests");
        reporter = Slf4jReporter.forRegistry(metrics)
                .outputTo(LoggerFactory.getLogger("com.splat.AccountServide"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10,TimeUnit.SECONDS);
    }

}
