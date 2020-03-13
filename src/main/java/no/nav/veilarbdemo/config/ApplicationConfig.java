package no.nav.veilarbdemo.config;

import no.nav.common.aktorregisterklient.AktorregisterHttpKlient;
import no.nav.common.aktorregisterklient.AktorregisterKlient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    // TODO: Hent dette fra config som igjen henter fra miljø
    public static final String APPLICATION_NAME = "veilarbdemo";

    @Bean
    public AktorregisterKlient aktorregisterKlient() {
        return new AktorregisterHttpKlient("localhost:1234", APPLICATION_NAME, () -> "");
    }

}
