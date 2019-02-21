package com.gdm.vehicleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VehicleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleApiApplication.class, args);
	}

}
