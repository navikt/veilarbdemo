package no.nav.fo.veilarbdemo;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/")
public class DemoRessurs {

    @GET
    public String get() {
        return "alt ok!";
    }

    @GET
    @Path("/feil")
    public String feil() {
        throw new IllegalStateException("feil!");
    }

}