package no.nav.veilarbdemo;

import no.nav.common.utils.SslUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeilarbdemoApp {

    public static void main(String[] args) {
        SslUtils.setupTruststore();
        SpringApplication.run(VeilarbdemoApp.class, args);
    }

}
