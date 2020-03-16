package no.nav.veilarbdemo.controller;

import no.nav.veilarbdemo.domain.Hello;
import no.nav.veilarbdemo.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/hello")
public class HelloWorldController {

    private final MetricsService metricsService;

    @Autowired
    public HelloWorldController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/{name}")
    public Hello hello(@PathVariable String name) {
        metricsService.incrementHelloWorldCalled();
        return new Hello(name);
    }

    @GetMapping("/bad")
    public void bad() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your request is not good enough");
    }

}
