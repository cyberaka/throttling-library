package com.cyberaka.practice.throttling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ThrottlingLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThrottlingLibraryApplication.class, args);
	}

}
