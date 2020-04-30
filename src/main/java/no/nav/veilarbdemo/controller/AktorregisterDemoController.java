package no.nav.veilarbdemo.controller;

import no.nav.veilarbdemo.service.AktorregisterDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aktor")
public class AktorregisterDemoController {

    private final AktorregisterDemoService aktorregisterDemoService;

    @Autowired
    public AktorregisterDemoController(AktorregisterDemoService aktorregisterDemoService) {
        this.aktorregisterDemoService = aktorregisterDemoService;
    }

    @GetMapping("/fnr")
    public String finnFnr(@RequestParam String aktorId) {
        return aktorregisterDemoService.hentFnr(aktorId);
    }

    @GetMapping("/aktorId")
    public String finnAktorId(@RequestParam String fnr) {
        return aktorregisterDemoService.hentAktorId(fnr);
    }

}
