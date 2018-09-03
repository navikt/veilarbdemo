package no.nav.fo.veilarbdemo;

import no.nav.apiapp.selftest.Helsesjekk;
import no.nav.apiapp.selftest.HelsesjekkMetadata;
import no.nav.sbl.rest.RestUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

import static no.nav.brukerdialog.security.oidc.provider.AzureADB2CConfig.AZUREAD_B2C_DISCOVERY_URL_PROPERTY_NAME;
import static no.nav.sbl.util.EnvironmentUtils.getRequiredProperty;

@Component
public class AzureAdHelsesjekk implements Helsesjekk {

    private static final String DISCOVERY_URL = getRequiredProperty(AZUREAD_B2C_DISCOVERY_URL_PROPERTY_NAME);
    private HelsesjekkMetadata helsesjekkMetadata = new HelsesjekkMetadata("discovery", DISCOVERY_URL, "henter metadata fra AzureAD", true);

    @Override
    public void helsesjekk() {
        RestUtils.withClient(c -> c.target(DISCOVERY_URL).request().get(Map.class));
    }

    @Override
    public HelsesjekkMetadata getMetadata() {
        return helsesjekkMetadata;
    }
}
