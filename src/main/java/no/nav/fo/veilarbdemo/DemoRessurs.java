package no.nav.fo.veilarbdemo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import no.nav.json.JsonUtils;
import no.nav.sbl.rest.RestUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.net.InetAddress;
import java.time.Duration;
import java.util.UUID;

import static java.util.concurrent.CompletableFuture.runAsync;

@Component
@Path("/")
@Slf4j
public class DemoRessurs {

    @GET
    public String get() {
        return ok();
    }

    @GET
    @Path("/ok")
    public String ok() {
        return "alt ok!";
    }

    @GET
    @Path("/job")
    public String job() {
        UUID uuid = UUID.randomUUID();
        String id = Long.toHexString(uuid.getMostSignificantBits()) + Long.toHexString(uuid.getLeastSignificantBits());

        runAsync(
                () -> {
                    MDC.put("jobId", id);
                    log.info("foo");
                    MDC.remove("jobId");
                }
        );


        return "job started!";
    }


    @GET
    @Path("/slow")
    @SneakyThrows
    public String slow() {
        long millis = Duration.ofMinutes(5).toMillis();
        log.info("sleeping {}ms", millis);
        Thread.sleep(millis);
        return "alt ok!";
    }

    @POST
    @Path("/pipe")
    public String pipe(String content) {
        return content;
    }

    @GET
    @Path("/feil")
    public String feil() {
        throw new IllegalStateException("feil!");
    }

    @GET
    @Path("/leader")
    @SneakyThrows
    public boolean leader() {
        String entity = RestUtils.withClient(client -> client
                .target("http://" + System.getenv("ELECTOR_PATH"))
                .request()
                .get()
                .readEntity(String.class)
        );

        LeaderResponse leader = JsonUtils.fromJson(entity, LeaderResponse.class);

        return InetAddress.getLocalHost().getHostName().equals(leader.getName());
    }
}
