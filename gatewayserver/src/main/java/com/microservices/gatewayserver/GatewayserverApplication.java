package com.microservices.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	@Bean
	public RouteLocator rewriteRouteConfig(RouteLocatorBuilder builder) {
		LocalDateTime now = LocalDateTime.now();

		// Format in 12-hour format with AM/PM
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a, dd-MM-yyyy");

		String formatted = now.format(formatter);
		return builder.routes()
				.route(p->p
						.path("/banking/accounts/**")
						.filters(f->f.rewritePath("/banking/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", formatted)
						)
						.uri("lb://ACCOUNTS")
				)
				.route(	p->p
						.path("/banking/loans/**")
						.filters(f->f.rewritePath("/banking/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", formatted)
						)
						.uri("lb://LOANS")

				)
				.route(p->p
						.path("/banking/cards/**")
						.filters(f->f.rewritePath("/banking/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", formatted)
						)
						.uri("lb://CARDS")

				).build();

	}

}
