package com.stackroute.GatewayServer;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin("*")
public class ApiGatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("UserService-MS", b -> b.path("/users/**")
                        .uri("http://localhost:8090"))
                .route("Authentication-MS", r -> r.path("/authorize/**")
                        .uri("http://localhost:8091"))
                .route("StockAPI-MS", r -> r.path("/api/v1/**")
                        .uri("http://localhost:8092"))
                .route("FavoriteStocks-MS", r -> r.path("/favorite/**")
                        .uri("http://localhost:8093"))
                .build();
    }
}
