package com.example.chapter02mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {"user.controller", "user.service", "user.dao", "user.bean"}) // 빈 설정 패키지 등록
@MapperScan("user.dao") // mapper 파일 
@EnableTransactionManagement // DB 트랜잭션
// @SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // DB 환경설정
@SpringBootApplication
public class Chapter02mysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter02mysqlApplication.class, args);
	}

}
