package com.noexit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NoExitApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoExitApplication.class, args);
	}

}
