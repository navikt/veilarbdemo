package no.nav.veilarbdemo.controller;

import no.nav.common.metrics.Event;
import no.nav.common.metrics.MetricsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/metrics-demo")
public class MetricsDemoController {

    private final MetricsClient influxMetricsClient;

    @Autowired
    public MetricsDemoController(MetricsClient influxMetricsClient) {
        this.influxMetricsClient = influxMetricsClient;
    }

    @GetMapping("/test1")
    public void test1() {
        Event event = new Event("veilarbdemo.metrikk.test1");
        event.addFieldToReport("intValue", new Random().nextInt() % 100);
        influxMetricsClient.report(event);
    }

}
