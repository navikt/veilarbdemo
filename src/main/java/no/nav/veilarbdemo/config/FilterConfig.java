package no.nav.veilarbdemo.config;

import no.nav.common.auth.oidc.filter.OidcAuthenticationFilter;
import no.nav.common.auth.oidc.filter.OidcAuthenticator;
import no.nav.common.auth.oidc.filter.OidcAuthenticatorConfig;
import no.nav.common.auth.subject.IdentType;
import no.nav.common.log.LogFilter;
import no.nav.common.rest.filter.SetStandardHttpHeadersFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static no.nav.common.auth.Constants.AZURE_AD_ID_TOKEN_COOKIE_NAME;
import static no.nav.common.auth.Constants.OPEN_AM_ID_TOKEN_COOKIE_NAME;
import static no.nav.common.auth.oidc.filter.OidcAuthenticator.fromConfig;
import static no.nav.common.utils.EnvironmentUtils.*;

@Configuration
@Profile("!local")
public class FilterConfig {

    @Bean
    public FilterRegistrationBean authenticationFilterRegistrationBean(EnvironmentProperties properties) {
        OidcAuthenticatorConfig azureAdConfig = new OidcAuthenticatorConfig()
                .withDiscoveryUrl(properties.getAzureAdDiscoveryUrl())
                .withClientId(properties.getAzureAdClientId())
                .withIdTokenCookieName(AZURE_AD_ID_TOKEN_COOKIE_NAME)
                .withIdentType(IdentType.InternBruker);

        OidcAuthenticatorConfig openAmConfig = new OidcAuthenticatorConfig()
                .withDiscoveryUrl(properties.getOpenAmDiscoveryUrl())
                .withClientId(properties.getOpenAmClientId())
                .withIdTokenCookieName(OPEN_AM_ID_TOKEN_COOKIE_NAME)
                .withIdentType(IdentType.InternBruker);

        FilterRegistrationBean<OidcAuthenticationFilter> registration = new FilterRegistrationBean<>();
        OidcAuthenticationFilter authenticationFilter = new OidcAuthenticationFilter(
                List.of(fromConfig(azureAdConfig), fromConfig(openAmConfig))
        );

        registration.setFilter(authenticationFilter);
        registration.setOrder(1);
        registration.addUrlPatterns("/api/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean logFilterRegistrationBean() {
        FilterRegistrationBean<LogFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new LogFilter(requireApplicationName(), isDevelopment().orElse(false)));
        registration.setOrder(2);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean setStandardHeadersFilterRegistrationBean() {
        FilterRegistrationBean<SetStandardHttpHeadersFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new SetStandardHttpHeadersFilter());
        registration.setOrder(3);
        registration.addUrlPatterns("/*");
        return registration;
    }

}
