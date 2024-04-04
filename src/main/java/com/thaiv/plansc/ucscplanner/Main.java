package com.thaiv.plansc.ucscplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.thaiv.plansc.ucscplanner.Main;

@SpringBootApplication
@EnableJpaRepositories("com.thaiv.plansc.coursedb.repositories")
@EntityScan("com.thaiv.plansc.coursedb.models")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
