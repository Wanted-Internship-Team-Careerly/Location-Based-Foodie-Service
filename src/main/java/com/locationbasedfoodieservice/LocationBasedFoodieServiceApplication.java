package com.locationbasedfoodieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LocationBasedFoodieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationBasedFoodieServiceApplication.class, args);
	}

}
