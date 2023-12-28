package com.eazy.loans;

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


@EnableJpaAuditing(auditorAwareRef="loansAuditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans Microservice REST API Documentation",
				description = "Eazy Loan Microservice REST API Documentation",
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
@ComponentScan({"com.eazy.loans","com.eazy.core"})
@EnableJpaRepositories("com.eazy.core")
@EntityScan("com.eazy.core")
@SpringBootApplication
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
