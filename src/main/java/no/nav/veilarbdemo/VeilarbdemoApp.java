package no.nav.veilarbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static no.nav.common.utils.EnvironmentUtils.getOptionalProperty;


@SpringBootApplication
public class VeilarbdemoApp {

    public static void main(String[] args) {
        if (!getOptionalProperty("javax.net.ssl.trustStore").isPresent()) {
            getOptionalProperty("NAV_TRUSTSTORE_PATH").ifPresent(path -> System.setProperty("javax.net.ssl.trustStore", path));
            getOptionalProperty("NAV_TRUSTSTORE_PASSWORD").ifPresent(passwd -> System.setProperty("javax.net.ssl.trustStorePassword", passwd));
        }

        SpringApplication.run(VeilarbdemoApp.class, args);
    }

}
