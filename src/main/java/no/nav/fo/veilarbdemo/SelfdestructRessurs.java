package no.nav.fo.veilarbdemo;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.lang.management.MemoryManagerMXBean;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;

@Component
@Path("/selfdestruct")
public class SelfdestructRessurs {

    private final Map<String, Object> map = new HashMap<>();

    @Path("/exit/{code}")
    @GET
    public void exit(@PathParam("code") int status) {
        System.exit(status);
    }

    @Path("/halt/{code}")
    @GET
    public void halt(@PathParam("code") int status) {
        Runtime.getRuntime().halt(status);
    }

    @GET
    @Path("/oom")
    public String get() {
        while (true) {
            wasteMemory();
        }
    }

    @GET
    @Path("/waste")
    public String wasteMemory() {
        List<Object> value = new ArrayList<>();
        map.put(UUID.randomUUID().toString(), value);
        for (int i = 0; i < 20_000; i++) {
            value.add(new byte[i]);
        }
        return "wasted memory!";
    }

}