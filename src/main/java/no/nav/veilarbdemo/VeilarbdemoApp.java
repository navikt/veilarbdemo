package no.nav.veilarbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VeilarbdemoApp {

    // TODO: Koble til postgres

    // TODO: Bruk 1 wiremock/okhttp for alle lokale tjenester

    public static void main(String[] args) {
        SpringApplication.run(VeilarbdemoApp.class, args);
    }

}
