package no.nav.veilarbdemo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SecretProperties {

    @Value("${app.secret.my_secret_password}")
    private String password;

}
