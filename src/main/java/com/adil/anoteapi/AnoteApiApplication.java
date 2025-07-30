package com.adil.anoteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AnoteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnoteApiApplication.class, args);
	}

}
