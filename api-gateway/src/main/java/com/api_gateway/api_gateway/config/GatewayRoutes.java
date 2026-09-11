package com.api_gateway.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class GatewayRoutes {

    @Bean
    public RouterFunction<ServerResponse> ticketServiceRoute() {
        return route("ticket-service")
                .GET("/ticket-service/**", http())
                .POST("/ticket-service/**", http())
                .PUT("/ticket-service/**", http())
                .DELETE("/ticket-service/**", http())
                .before(stripPrefix(1))
                .filter(lb("TICKET-SERVICE"))
                .build();
    }
}