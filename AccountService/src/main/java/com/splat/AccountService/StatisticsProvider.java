package com.splat.AccountService;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class StatisticsProvider {

    private MetricRegistry metrics;
    public Meter getRequests;
    public  Meter postRequests;
    private Slf4jReporter reporter;

    StatisticsProvider(){
        setMetrics();
    }

    private void setMetrics(){
        metrics = new MetricRegistry();
        getRequests = metrics.meter("getRequests");
        postRequests = metrics.meter("postRequests");
        reporter = Slf4jReporter.forRegistry(metrics)
                .outputTo(LoggerFactory.getLogger("com.splat.AccountService"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10,TimeUnit.SECONDS);
    }

    public void dropMetrics(){
        reporter.stop();
        reporter.close();
        setMetrics();
    }

}
