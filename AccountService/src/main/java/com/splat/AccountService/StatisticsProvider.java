package com.splat.AccountService;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.stereotype.Component;

@Component
public class StatisticsProvider {

    private static final MetricRegistry metrics = new MetricRegistry();
    public final Meter getRequests = metrics.meter("getRequests");
    public final Meter postRequests = metrics.meter("postRequests");

}
