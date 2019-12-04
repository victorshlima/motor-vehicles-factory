package com.motorcompany.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
		basePackages = "com.motorcompany.config.dao")
@SpringBootApplication
public class RestVoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestVoteApplication.class, args);
	}
}
