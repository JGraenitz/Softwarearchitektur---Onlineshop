package com.rsr.api_gateway;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
class ApiGatewayApplicationTests {

	@Container
	private static final GenericContainer<?> keycloakContainer = new GenericContainer<>("ghcr.io/rsr-by-aypa/rsr_keycloak:latest")
			.withExposedPorts(8080)
			.withEnv("KEYCLOAK_USER", "admin")
			.withEnv("KEYCLOAK_PASSWORD", "admin")
			.waitingFor(Wait.forHttp("/auth"));

	@BeforeAll
	static void beforeAll() {
		keycloakContainer.start();
	}

	@DynamicPropertySource
	static void dynamicProperties(DynamicPropertyRegistry registry) {
		// Provide dynamic properties for your Spring Boot application
		registry.add("spring.security.oauth2.client.provider.keycloak.issuer-uri",
				() -> "http://" + keycloakContainer.getHost() + ":" + keycloakContainer.getMappedPort(8080) + "/auth/realms/RockSolidRemedies");
		registry.add("spring.security.oauth2.client.registration.keycloak.client-id",
				() -> "rsr-api-gateway");
		registry.add("spring.security.oauth2.client.registration.keycloak.client-secret",
				() -> "gs9VfLEeM268VHLrxP0woZ46OzaHnTW6");
	}

	@Test
	void contextLoads() {

	}

}
