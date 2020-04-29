package no.nav.veilarbdemo.config;

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

import java.util.Collections;

import static no.nav.common.nais.NaisUtils.getCredentials;
import static no.nav.common.oidc.Constants.AZURE_AD_ID_TOKEN_COOKIE_NAME;
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

    private OidcAuthenticatorConfig createAzureAdAuthenticatorConfig(String discoveryUrl, String clientId) {
        return new OidcAuthenticatorConfig()
                .withDiscoveryUrl(discoveryUrl)
                .withClientId(clientId)
                .withIdTokenCookieName(AZURE_AD_ID_TOKEN_COOKIE_NAME)
                .withIdentType(IdentType.InternBruker);
    }

    @Bean
    public FilterRegistrationBean authenticationRegistrationBean(EnvironmentProperties properties) {
        OidcAuthenticatorConfig azureAdConfig = createAzureAdAuthenticatorConfig(
                properties.getAzureAdDiscoveryUrl(), properties.getAzureAdClientId()
        );

        FilterRegistrationBean<OidcAuthenticationFilter> registration = new FilterRegistrationBean<>();
        OidcAuthenticationFilter authenticationFilter = new OidcAuthenticationFilter(
                Collections.singletonList(OidcAuthenticator.fromConfig(azureAdConfig))
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
