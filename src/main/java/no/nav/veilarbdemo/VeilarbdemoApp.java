package no.nav.veilarbdemo;

import no.nav.veilarbdemo.client.RestClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static no.nav.common.utils.EnvironmentUtils.getOptionalProperty;


@SpringBootApplication
public class VeilarbdemoApp {

    public static void main(String[] args) {
        System.setProperty("java.net.useSystemProxies", "true");
        if (!getOptionalProperty("javax.net.ssl.trustStore").isPresent()) {
            getOptionalProperty("NAV_TRUSTSTORE_PATH").ifPresent(path -> System.setProperty("javax.net.ssl.trustStore", path));
            getOptionalProperty("NAV_TRUSTSTORE_PASSWORD").ifPresent(passwd -> System.setProperty("javax.net.ssl.trustStorePassword", passwd));
        }

        Request openAmRequest = new Request.Builder()
                .url("https://isso-q.adeo.no/isso/oauth2/.well-known/openid-configuration")
                .build();

        Request azureAdRequest = new Request.Builder()
                .url("https://login.microsoftonline.com/966ac572-f5b7-4bbe-aa88-c76419c0f851/.well-known/openid-configuration")
                .build();

        try {
            OkHttpClient client = RestClient.baseClient();

            Response openAmResponse = client.newCall(openAmRequest).execute();
            System.out.println(openAmResponse.body().string());

            Response azureAdResponse = client.newCall(azureAdRequest).execute();
            System.out.println(azureAdResponse.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(VeilarbdemoApp.class, args);
    }

}
