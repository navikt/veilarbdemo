package no.nav.veilarbdemo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.env")
public class EnvironmentProperties {

    private String aktorregisterUrl;

    private String azureAdDiscoveryUrl;

    private String azureAdClientId;

}
