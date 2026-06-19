package com.eventledger.account_service.config;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TraceFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        String traceId = request.getParameter("X-Trace-Id");

        if (traceId != null) {
            MDC.put("traceId", traceId);
        }

        chain.doFilter(request, response);

        MDC.clear();
    }
}