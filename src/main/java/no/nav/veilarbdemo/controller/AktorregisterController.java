package no.nav.veilarbdemo.controller;

import no.nav.common.aktorregisterklient.AktorregisterKlient;
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

    private final AktorregisterKlient aktorregisterKlient;

    @Autowired
    public AktorregisterController(AktorregisterKlient aktorregisterKlient) {
        this.aktorregisterKlient = aktorregisterKlient;
    }

    @GetMapping("/fnr")
    public String finnFnr(@RequestParam String aktorId) {
        return aktorregisterKlient.hentFnr(aktorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/aktorId")
    public String finnAktorId(@RequestParam String fnr) {
        return aktorregisterKlient.hentAktorId(fnr).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
