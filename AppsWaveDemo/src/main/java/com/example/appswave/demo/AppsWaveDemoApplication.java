package com.example.appswave.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppsWaveDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppsWaveDemoApplication.class, args);
	}

}
