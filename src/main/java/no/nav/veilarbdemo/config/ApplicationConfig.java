package no.nav.veilarbdemo.config;

import no.nav.brukerdialog.security.domain.IdentType;
import no.nav.common.aktorregisterklient.AktorregisterHttpKlient;
import no.nav.common.aktorregisterklient.AktorregisterKlient;
import no.nav.common.oidc.auth.OidcAuthenticatorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static no.nav.common.oidc.Constants.AZURE_AD_ID_TOKEN_COOKIE_NAME;

@Configuration
public class ApplicationConfig {

    // TODO: Hent dette fra config som igjen henter fra miljø
    public static final String APPLICATION_NAME = "veilarbdemo";

    @Bean
    public AktorregisterKlient aktorregisterKlient() {
        return new AktorregisterHttpKlient("localhost:1234", APPLICATION_NAME, () -> "");
    }

    private OidcAuthenticatorConfig createAzureAdB2CAuthenticatorConfig() {
        String discoveryUrl = "https://login.microsoftonline.com/966ac572-f5b7-4bbe-aa88-c76419c0f851/.well-known/openid-configuration";
        String clientId = "38e07d31-659d-4595-939a-f18dce3446c5";

        return new OidcAuthenticatorConfig()
                .withDiscoveryUrl(discoveryUrl)
                .withClientId(clientId)
                .withIdTokenCookieName(AZURE_AD_ID_TOKEN_COOKIE_NAME)
                .withIdentType(IdentType.InternBruker);
    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean<OidcAuthenticationFilter> registration = new FilterRegistrationBean<>();
//        OidcAuthenticationFilter authenticationFilter = new OidcAuthenticationFilter(
//                Collections.singletonList(OidcAuthenticator.fromConfig(createOpenAmAuthenticatorConfig())),
//                Collections.singletonList("/internal/.*")
//        );
//
//        registration.setFilter(authenticationFilter);
//        registration.setOrder(1);
//        registration.addUrlPatterns("/*");
//        return registration;
//    }

}
