package com.techqwerty.spring_boot_ecommerce_api_carrygo_store;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "CarryGo Ecommerce Spring Boot Rest API Documentation", description = "Spring Boot REST API Documentation for CarryGo Ecommerce Store", version = "v1.0", contact = @Contact(name = "Rilwan Amoo", email = "amoowale@gmail.com", url = "https://www.techqwerty.com"), license = @License(name = "Apache 2.0", url = "https://www.techqwerty.com")), externalDocs = @ExternalDocumentation(description = "Spring Boot CarryGo Ecommerce API Documentation", url = "https://www.techqwerty.com"))
public class SpringBootEcommerceApiCarrygoStoreApplication {

	// configure Model Mapper as a Bean. Add the bean to startup class.
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommerceApiCarrygoStoreApplication.class, args);
	}

}
