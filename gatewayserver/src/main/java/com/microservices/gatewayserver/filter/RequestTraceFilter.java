package com.microservices.gatewayserver.filter;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1)
@Component

public class RequestTraceFilter  {
    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);


    @Bean
    public GlobalFilter correlationIdFilter(FilterUtility filterUtility) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String correlationId = filterUtility.getCorrelationId(headers);

            if (correlationId == null) {
                correlationId = java.util.UUID.randomUUID().toString();
                exchange = filterUtility.setCorrelationId(exchange, correlationId);
                logger.debug("Generated new correlation id: {}", correlationId);
            } else {
                logger.debug("Found existing correlation id: {}", correlationId);
            }

            // Important: pass updated exchange forward
            return chain.filter(exchange);
        };
    }


}
