package com.thaiv.ucscplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;


@SpringBootApplication
@EnableJpaRepositories("com.thaiv.coursedb.repositories")
@EntityScan("com.thaiv.coursedb.models")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
