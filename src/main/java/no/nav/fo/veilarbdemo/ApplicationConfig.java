package no.nav.fo.veilarbdemo;

import no.nav.apiapp.ApiApplication;
import no.nav.apiapp.config.ApiAppConfigurator;
import no.nav.sbl.featuretoggle.unleash.UnleashServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import no.nav.sbl.featuretoggle.unleash.UnleashService;


@Configuration
@Import({
        DemoRessurs.class,
        SelfdestructRessurs.class,
        DebugRessurs.class,
        ControlledSelfTestResource.class,
        ConfigMapRessurs.class
})
public class ApplicationConfig implements ApiApplication {

    @Override
    public String getContextPath() {
        return "/veilarbdemo";
    }

    @Bean
    public UnleashService unleashService() {
        UnleashServiceConfig config = UnleashServiceConfig.builder()
                .applicationName("veilarbdemo")
                .unleashApiUrl("https://unleash.nais.adeo.no/api/")
                .build();
        return new UnleashService(config);
    }

    @Override
    public void configure(ApiAppConfigurator apiAppConfigurator) {
    }
}
