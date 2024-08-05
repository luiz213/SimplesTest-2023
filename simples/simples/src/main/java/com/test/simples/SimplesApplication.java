package com.test.simples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.test.simples", "utils"})
public class SimplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplesApplication.class, args);
	}

}
