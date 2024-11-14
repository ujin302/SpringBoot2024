package com.example.chapter04jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"member.controller"})
@SpringBootApplication
public class Chapter04jpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter04jpaApplication.class, args);
	}

}
