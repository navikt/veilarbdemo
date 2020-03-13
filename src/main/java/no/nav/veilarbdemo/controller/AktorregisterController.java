package no.nav.veilarbdemo.controller;

import no.nav.veilarbdemo.client.AktorregisterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/aktor")
public class AktorregisterController {

    private final AktorregisterClient aktorregisterClient;

    @Autowired
    public AktorregisterController(AktorregisterClient aktorregisterClient) {
        this.aktorregisterClient = aktorregisterClient;
    }

    @GetMapping("/fnr")
    public String finnFnr(@RequestParam String aktorId) {
        return aktorregisterClient.hentFnr(aktorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/aktorId")
    public String finnAktorId(@RequestParam String fnr) {
        return aktorregisterClient.hentAktorId(fnr).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
