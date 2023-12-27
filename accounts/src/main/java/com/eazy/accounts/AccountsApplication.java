package com.eazy.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice REST API Documentation",
				description = "Ezy Accounts Microservice REST API Documentation",
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
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
