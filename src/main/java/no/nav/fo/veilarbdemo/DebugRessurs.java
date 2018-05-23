package no.nav.fo.veilarbdemo;

import no.nav.sbl.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.stream.IntStream;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static no.nav.sbl.util.EnvironmentUtils.EnviromentClass.Q;
import static no.nav.sbl.util.EnvironmentUtils.EnviromentClass.T;
import static no.nav.sbl.util.EnvironmentUtils.isEnvironmentClass;
import static no.nav.sbl.util.StringUtils.of;

@Component
@Path("/debug")
public class DebugRessurs {

    @GET
    @Produces(TEXT_PLAIN)
    public String get(
            @Context HttpServletRequest httpServletRequest,
            @Context UriInfo uriInfo
    ) {
        Printer printer = new Printer();

        // litt ekstra sikring her for hvis noen ikke er observante og copy-paster dette
        if (isEnvironmentClass(T) || isEnvironmentClass(Q)) {


            Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
            printer.println(uriInfo.getRequestUri().toString());
            printer.println("%s %s%s",
                    httpServletRequest.getMethod(),
                    httpServletRequest.getRequestURI(),
                    of(httpServletRequest.getQueryString()).map(s -> "?" + s).orElse("")
            );

            printer.printSection("Request Headers");
            Collections.list(headerNames)
                    .stream()
                    .map(s -> formatKeyValue(s, httpServletRequest.getHeader(s)))
                    .forEach(printer::println);

            printer.printSection("System properties");
            System.getProperties().forEach((k, v) -> printer.println(formatKeyValue(k.toString(), v.toString())));

            printer.printSection("Environment");
            System.getenv().forEach((k, v) -> printer.println(formatKeyValue(k, v)));

        }
        return printer.toString();
    }

    private String formatKeyValue(String key, String value) {
        return String.format("%-30s %s", key, value);
    }

    private static class Printer {
        private final StringBuilder stringBuilder = new StringBuilder();

        public void println(String formattedMessage, Object... args) {
            stringBuilder.append(String.format(formattedMessage, args));
            lineBreak();
        }

        public void printSection(String sectionName) {
            lineBreak();
            lineBreak();
            stringBuilder.append(sectionName);
            lineBreak();
            IntStream.range(0, sectionName.length()).forEach(i -> stringBuilder.append("="));
            lineBreak();
            lineBreak();
        }

        private void lineBreak() {
            stringBuilder.append("\n");
        }

        @Override
        public String toString() {
            return stringBuilder.toString();
        }

    }

}