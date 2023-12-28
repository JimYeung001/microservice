package com.eazy.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@EnableJpaAuditing(auditorAwareRef="cardsAuditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards Microservice REST API Documentation",
				description = "Eazy Cards Microservice REST API Documentation",
				version = "V1",
				contact = @Contact(
						name = "Jim Yeung",
						email = "qzy114@gmail.com",
						url = "http://localhost:8080/swagger-ui/index.html#/"
						),
				license = @License(
						name = "Apache 2.0"
						)
				)
		)
@ComponentScan({"com.eazy.cards","com.eazy.core"})
@EnableJpaRepositories("com.eazy.core")
@EntityScan("com.eazy.core")
@SpringBootApplication
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
