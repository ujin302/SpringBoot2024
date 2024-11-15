package com.example.chapter04jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"member.*"})
@EntityScan(basePackages = {"member.entity"})
@EnableJpaRepositories(basePackages = {"member.dao"})
@SpringBootApplication
public class Chapter04jpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter04jpaApplication.class, args);
	}

}
