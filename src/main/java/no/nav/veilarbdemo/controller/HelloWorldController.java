package no.nav.veilarbdemo.controller;

import no.nav.veilarbdemo.domain.Hello;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/hello")
public class HelloWorldController {

    @GetMapping("/{name}")
    public Hello hello(@PathVariable String name) {
        return new Hello(name);
    }

    @GetMapping("/bad")
    public void bad() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your request is not good enough");
    }

}
