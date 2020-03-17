package no.nav.veilarbdemo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class EnvironmentProperties {

    public final static List<String> ENVIRONMENT_VARIABLES = List.of(
            "AKTOERREGISTER_API_V1_URL",
            "LOGINSERVICE_OIDC_DISCOVERYURI",
            "LOGINSERVICE_OIDC_CLIENT_ID"
    );

    @Value("${app.env.AKTOERREGISTER_API_V1_URL}")
    private String aktorregisterUrl;

    @Value("${app.env.LOGINSERVICE_OIDC_DISCOVERYURI}")
    private String azureAdDiscoveryUrl;

    @Value("${app.env.LOGINSERVICE_OIDC_CLIENT_ID}")
    private String azureAdClientId;

}
