package com.motorcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
		basePackages = "com.motorcompany.dao")
@SpringBootApplication
public class MotorVehicleFactoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(MotorVehicleFactoryApplication.class, args);
	}
}
