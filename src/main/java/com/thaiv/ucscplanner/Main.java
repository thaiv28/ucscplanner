package com.thaiv.ucscplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.thaiv.coursedb.repositories")
@EntityScan("com.thaiv.coursedb.models")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
