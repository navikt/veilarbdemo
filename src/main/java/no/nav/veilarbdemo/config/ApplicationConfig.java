package no.nav.veilarbdemo.config;

import no.nav.common.abac.Pep;
import no.nav.common.abac.VeilarbPep;
import no.nav.common.aktorregisterklient.AktorregisterHttpKlient;
import no.nav.common.aktorregisterklient.AktorregisterKlient;
import no.nav.common.aktorregisterklient.CachedAktorregisterKlient;
import no.nav.common.nais.NaisUtils;
import no.nav.common.sts.NaisSystemUserTokenProvider;
import no.nav.common.sts.SystemUserTokenProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static no.nav.common.nais.NaisUtils.getCredentials;

@Configuration
@EnableConfigurationProperties(EnvironmentProperties.class)
@Profile("!local")
public class ApplicationConfig {

    public final static String APPLICATION_NAME = "veilarbdemo";

    private final String serviceUsername;

    private final String servicePassword;

    public ApplicationConfig() {
        NaisUtils.Credentials serviceUser = getCredentials("service_user");
        this.serviceUsername = serviceUser.username;
        this.servicePassword = serviceUser.password;
    }

    @Bean
    public SystemUserTokenProvider systemUserTokenProvider(EnvironmentProperties properties) {
        return new NaisSystemUserTokenProvider(properties.getStsDiscoveryUrl(), serviceUsername, servicePassword);
    }

    @Bean
    public AktorregisterKlient aktorregisterKlient(EnvironmentProperties properties, SystemUserTokenProvider tokenProvider) {
        AktorregisterKlient aktorregisterKlient = new AktorregisterHttpKlient(
                properties.getAktorregisterUrl(), APPLICATION_NAME, tokenProvider::getSystemUserToken
        );
        return new CachedAktorregisterKlient(aktorregisterKlient);
    }

    @Bean
    public Pep veilarbPep(EnvironmentProperties properties) {
        return new VeilarbPep(properties.getAbacUrl(), serviceUsername, servicePassword);
    }

}
