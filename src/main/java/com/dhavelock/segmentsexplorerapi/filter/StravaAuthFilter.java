package com.dhavelock.segmentsexplorerapi.filter;

import com.strava.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StravaAuthFilter implements Filter {

    private final ApiClient apiClient;

    public StravaAuthFilter(@Qualifier("stravaApiClient") ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        final String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            apiClient.setAccessToken(token);
        }

        chain.doFilter(req, res);
    }
}
