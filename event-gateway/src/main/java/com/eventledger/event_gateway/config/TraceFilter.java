package main.java.com.eventledger.event_gateway.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class TraceFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        String traceId = UUID.randomUUID().toString();

        MDC.put("traceId", traceId);

        ((HttpServletResponse) response)
                .addHeader("X-Trace-Id", traceId);

        chain.doFilter(request, response);

        MDC.clear();
    }
}