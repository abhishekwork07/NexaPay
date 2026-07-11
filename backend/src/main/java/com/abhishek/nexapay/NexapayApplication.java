package com.abhishek.nexapay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NexapayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexapayApplication.class, args);
	}

}
