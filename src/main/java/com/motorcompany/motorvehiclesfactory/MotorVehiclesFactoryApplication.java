package com.motorcompany.motorvehiclesfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableSpringConfigured
public class MotorVehiclesFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotorVehiclesFactoryApplication.class, args);
	}

}
