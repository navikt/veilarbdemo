package no.nav.veilarbdemo.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.BiFunction;

public class SetHeaderFilter implements Filter {

    private final BiFunction<HttpServletRequest, HttpServletResponse, Map<String, String>> createHeaders;

    public SetHeaderFilter(Map<String, String> headers) {
        this.createHeaders = (req, resp) -> headers;
    }

    public SetHeaderFilter(BiFunction<HttpServletRequest, HttpServletResponse, Map<String, String>> createHeaders) {
        this.createHeaders = createHeaders;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        createHeaders.apply(request, response).forEach(response::setHeader);

        chain.doFilter(servletRequest, servletResponse);
    }

}
