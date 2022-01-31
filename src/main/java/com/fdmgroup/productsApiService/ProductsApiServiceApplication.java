package com.fdmgroup.productsApiService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class ProductsApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiServiceApplication.class, args);
	}

}
