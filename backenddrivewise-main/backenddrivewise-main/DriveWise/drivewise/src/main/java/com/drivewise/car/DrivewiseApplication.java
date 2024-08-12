package com.drivewise.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories 
public class DrivewiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrivewiseApplication.class, args);
	}

}
