package com.blankfactor.MaintainMe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync

public class MaintainMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintainMeApplication.class, args);
	}

}
