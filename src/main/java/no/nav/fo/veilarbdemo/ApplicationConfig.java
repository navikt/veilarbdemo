package no.nav.fo.veilarbdemo;

import no.nav.apiapp.ApiApplication;
import no.nav.apiapp.config.ApiAppConfigurator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        DemoRessurs.class,
        SelfdestructRessurs.class,
        DebugRessurs.class
})
public class ApplicationConfig implements ApiApplication.NaisApiApplication {

    @Override
    public boolean brukSTSHelsesjekk() {
        return false;
    }

    @Override
    public String getContextPath() {
        return "/veilarbdemo";
    }

    @Override
    public void configure(ApiAppConfigurator apiAppConfigurator) {
    }
}