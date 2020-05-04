package no.nav.veilarbdemo.config;

import no.nav.common.abac.Pep;
import no.nav.common.aktorregisterklient.AktorregisterKlient;
import no.nav.veilarbdemo.mock.AktorregisterKlientMock;
import no.nav.veilarbdemo.mock.VeilarbPepMock;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EnvironmentProperties.class)
public class ApplicationTestConfig {

    @Bean
    public AktorregisterKlient aktorregisterKlient() {
        return new AktorregisterKlientMock();
    }

    @Bean
    public Pep veilarbPep() {
        return new VeilarbPepMock();
    }

}
