package no.nav.veilarbdemo;

import no.nav.common.aktorregisterklient.AktorregisterKlient;
import no.nav.veilarbdemo.config.EnvironmentProperties;
import no.nav.veilarbdemo.mock.AktorregisterKlientMock;
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

}
