package com.eazy.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	
	@Bean
	public RouteLocator eazyRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/easy/accounts/**")
						.filters(f->f.rewritePath("/easy/accounts/(?<segment>.*)", "/${segment}"))
						.uri("lb://ACCOUNTS"))
				.route(p -> p.path("/easy/loans/**")
						.filters(f->f.rewritePath("/easy/loans/(?<segment>.*)", "/${segment}"))
						.uri("lb://LOANS"))
				.route(p -> p.path("/easy/cards/**")
						.filters(f->f.rewritePath("/easy/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARDS")).build();
	}
}