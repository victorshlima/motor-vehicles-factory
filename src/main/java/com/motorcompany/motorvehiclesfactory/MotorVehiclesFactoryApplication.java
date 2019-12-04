package com.motorcompany.motorvehiclesfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan("com.motorcompany.motorvehiclesfactory.config")
@SpringBootApplication
public class MotorVehiclesFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotorVehiclesFactoryApplication.class, args);
	}

}
