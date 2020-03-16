package no.nav.veilarbdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class HealthController {

    @GetMapping("/isAlive")
    public String isAlive() {
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
