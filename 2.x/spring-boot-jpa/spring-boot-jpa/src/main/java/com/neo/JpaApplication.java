package com.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
}
