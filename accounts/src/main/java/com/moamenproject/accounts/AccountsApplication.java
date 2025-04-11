package com.moamenproject.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts Microservice REST API Documentation",
								description = "EazyBank Accounts Microservice REST API Documentation",
								version = "v1",
								contact = @Contact(
									name = "Moamen Ahmed",
									email = "abdelmoamenahmed99@gmail.com",
									url = "moamen.com"
									),
									license = @License(
										name = "Apache 2.0",
										url = "moamen.com"
									)), externalDocs = @ExternalDocumentation(
										description = "EazyBank Accounts microservice Documentation",
										url = "http://localhost:8080/swagger-ui/index.htm"
									))
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
