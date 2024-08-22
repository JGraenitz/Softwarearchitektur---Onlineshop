package com.rsr.api_gateway.security.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserIdFilter extends AbstractGatewayFilterFactory<UserIdFilter.Config> {

    public UserIdFilter() {
        super(Config.class);
    }

    public static class Config {
        // Configuration properties for your filter
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return ReactiveSecurityContextHolder.getContext()
                    .map(context -> context.getAuthentication())
                    .flatMap(authentication -> {
                        if (authentication != null && authentication.isAuthenticated()) {
                            String userId = getUserIdFromAuthentication(authentication);
                            // Hier sicherstellen, dass der Pfad korrekt zusammengesetzt wird
                            String newPath = exchange.getRequest().getURI().getPath() + "/" + userId;
                            System.out.println("New Path: " + newPath); // Debug-Log
                            ServerHttpRequest request = exchange.getRequest().mutate().path(newPath).build();
                            ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();
                            return chain.filter(mutatedExchange);
                        }
                        return chain.filter(exchange);
                    }).then(Mono.fromRunnable(() -> System.out.println("Filter completed"))); // Debug-Log
        };
    }

    private String getUserIdFromAuthentication(Authentication authentication) {
        // Assuming the user ID is stored in the "sub" claim of the JWT token
        return authentication.getName();
    }
}
