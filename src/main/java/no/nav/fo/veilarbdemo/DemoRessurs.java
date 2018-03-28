package no.nav.fo.veilarbdemo;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Component
@Path("/")
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

}