package no.nav.veilarbdemo.config;

import no.nav.common.auth.oidc.filter.OidcAuthenticationFilter;
import no.nav.common.auth.oidc.filter.OidcAuthenticator;
import no.nav.common.auth.oidc.filter.OidcAuthenticatorConfig;
import no.nav.common.auth.subject.IdentType;
import no.nav.veilarbdemo.utils.SetHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static no.nav.common.auth.Constants.OPEN_AM_ID_TOKEN_COOKIE_NAME;
import static no.nav.veilarbdemo.utils.HttpFilterHeaders.ALL_HEADERS;

@Configuration
@Profile("!local")
public class FilterConfig {

    @Bean
    public FilterRegistrationBean authenticationFilterRegistrationBean(EnvironmentProperties properties) {
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
        registration.addUrlPatterns("/api/*");
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
