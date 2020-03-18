package no.nav.veilarbdemo.controller;

import no.nav.veilarbdemo.utils.health.HealthCheck;
import no.nav.veilarbdemo.utils.health.HealthCheckResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class HealthController {

    private HealthCheck dummyHealthCheck = () -> {
        return Math.random() > .5
                ? HealthCheckResult.healthy()
                : HealthCheckResult.unhealthy("Klarte ikke å nå tjenesten aktørregister");
    };

    @GetMapping("/isAlive")
    public String isAlive() {
        // HealthChecker.check(Collections.singletonList(dummyHealthCheck));
        return "Alive";
    }

    @GetMapping("/isReady")
    public String isReady() {
        return "Ready";
    }

    @GetMapping("/selftest")
    public String selftest() {
        return "Selftest";
    }

}
