package no.nav.veilarbdemo;

import no.nav.veilarbdemo.config.EnvironmentProperties;
import no.nav.veilarbdemo.utils.PropertyUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class VeilarbdemoApp {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(VeilarbdemoApp.class);

        Properties properties = new Properties();
        PropertyUtils.addPropertiesFromEnv(EnvironmentProperties.ENVIRONMENT_VARIABLES, properties);
        PropertyUtils.addPropertiesFromFile("TODO: Filepath to secret creds", properties);

        application.setDefaultProperties(properties);
        application.run(args);
    }

}
