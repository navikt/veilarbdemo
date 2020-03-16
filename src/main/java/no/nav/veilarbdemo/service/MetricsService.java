package no.nav.veilarbdemo.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final Counter helloWorldCalledCounter;

    @Autowired
    public MetricsService(MeterRegistry meterRegistry) {
        helloWorldCalledCounter = meterRegistry.counter("hello_world_called", "tagName1", "tagValue1");
    }

    public void incrementHelloWorldCalled() {
        helloWorldCalledCounter.increment();
    }

}
