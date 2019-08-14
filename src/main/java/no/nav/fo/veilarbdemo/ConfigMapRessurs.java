package no.nav.fo.veilarbdemo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Component
@Path("/configmap")
@Slf4j
public class ConfigMapRessurs {

    @GET
    @Produces(TEXT_PLAIN)
    @Path("/{key}")
    public String get(
            @PathParam("key") String key
    ) {
        return getConfigMap("pus", key);
    }

    @SneakyThrows
    public static String  getConfigMap(String configMap, String propertyName) {
        String filePath = String.format("/var/run/configmaps/%s/%s", configMap, propertyName);
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return lines.stream().findFirst().orElse(null);
    }

}