package com.example.thymeleaftest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"thymeleaf.controller", "thymeleaf.been"})
@SpringBootApplication
public class ThymeleaftestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleaftestApplication.class, args);
	}

}
