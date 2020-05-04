package no.nav.veilarbdemo.config;

import no.nav.common.abac.Pep;
import no.nav.common.abac.VeilarbPep;
import no.nav.common.aktorregisterklient.AktorregisterHttpKlient;
import no.nav.common.aktorregisterklient.AktorregisterKlient;
import no.nav.common.aktorregisterklient.CachedAktorregisterKlient;
import no.nav.common.auth.IdentType;
import no.nav.common.nais.NaisUtils;
import no.nav.common.oidc.SystemUserTokenProvider;
import no.nav.common.oidc.auth.OidcAuthenticationFilter;
import no.nav.common.oidc.auth.OidcAuthenticator;
import no.nav.common.oidc.auth.OidcAuthenticatorConfig;
import no.nav.veilarbdemo.utils.SetHeaderFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static no.nav.common.nais.NaisUtils.getCredentials;
import static no.nav.common.oidc.Constants.OPEN_AM_ID_TOKEN_COOKIE_NAME;
import static no.nav.veilarbdemo.utils.HttpFilterHeaders.ALL_HEADERS;

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
        return new SystemUserTokenProvider(properties.getStsDiscoveryUrl(), serviceUsername, servicePassword);
    }

    @Bean
    public AktorregisterKlient aktorregisterKlient(EnvironmentProperties properties, SystemUserTokenProvider tokenProvider) {
        AktorregisterKlient aktorregisterKlient = new AktorregisterHttpKlient(
                properties.getAktorregisterUrl(), APPLICATION_NAME, tokenProvider::getSystemUserAccessToken
        );
        return new CachedAktorregisterKlient(aktorregisterKlient);
    }

    @Bean
    public Pep veilarbPep(EnvironmentProperties properties) {
        return new VeilarbPep(properties.getAbacUrl(), serviceUsername, servicePassword);
    }

    @Bean
    public FilterRegistrationBean authenticationRegistrationBean(EnvironmentProperties properties) {
//        OidcAuthenticatorConfig azureAdConfig = new OidcAuthenticatorConfig()
//                .withDiscoveryUrl(properties.getAzureAdDiscoveryUrl())
//                .withClientId(properties.getAzureAdClientId())
//                .withIdTokenCookieName(AZURE_AD_ID_TOKEN_COOKIE_NAME)
//                .withIdentType(IdentType.InternBruker);

        OidcAuthenticatorConfig openAmConfig = new OidcAuthenticatorConfig()
                .withDiscoveryUrl(properties.getOpenAmDiscoveryUrl())
                .withClientId(properties.getOpenAmClientId())
                .withIdTokenCookieName(OPEN_AM_ID_TOKEN_COOKIE_NAME)
                .withIdentType(IdentType.InternBruker);

        FilterRegistrationBean<OidcAuthenticationFilter> registration = new FilterRegistrationBean<>();
        OidcAuthenticationFilter authenticationFilter = new OidcAuthenticationFilter(
                List.of(OidcAuthenticator.fromConfig(openAmConfig))
        );

        registration.setFilter(authenticationFilter);
        registration.setOrder(1);
        registration.addUrlPatterns("/api/.*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean httpFilterRegistrationBean() {
        FilterRegistrationBean<SetHeaderFilter> registration = new FilterRegistrationBean<>();
        SetHeaderFilter setHeaderFilter = new SetHeaderFilter(ALL_HEADERS);

        registration.setFilter(setHeaderFilter);
        registration.setOrder(10);
        registration.addUrlPatterns("/*");
        return registration;
    }

}
