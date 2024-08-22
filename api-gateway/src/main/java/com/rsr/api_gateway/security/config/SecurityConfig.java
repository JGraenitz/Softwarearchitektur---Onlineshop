package com.rsr.api_gateway.security.config;

import com.rsr.api_gateway.security.converter.CustomJwtAuthenticationConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.client.provider.keycloak.jwk-uri}")
    private String jwkUri;

    @Value("${spring.security.oauth2.client.provider.keycloak.login-uri}")
    private String kcLoginURI;
    private final Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter = new CustomJwtAuthenticationConverter();

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/admin/**").hasRole("client_admin")
                        .anyExchange().hasRole("client_user")
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                );

        return http.build();
    }

    @Bean
    public ServerAuthenticationEntryPoint authenticationEntryPoint() {
        LOGGER.info("Entered authenticationEntryPoint");
        return (exchange, ex) -> {
            if (ex != null) {
                // Perform redirect to Keycloak login
                LOGGER.info("Entered if-Case. Redirecting...");
                URI keycloakLoginUri = URI.create(kcLoginURI);
                exchange.getResponse().setStatusCode(HttpStatus.SEE_OTHER);
                exchange.getResponse().getHeaders().setLocation(keycloakLoginUri);
                return exchange.getResponse().setComplete();
            }
            return Mono.error(ex);
        };
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        return NimbusReactiveJwtDecoder
                .withJwkSetUri(jwkUri)
                .build();
    }
}
