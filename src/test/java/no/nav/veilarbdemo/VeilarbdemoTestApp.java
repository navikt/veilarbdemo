package no.nav.veilarbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeilarbdemoTestApp {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(VeilarbdemoTestApp.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }

}
