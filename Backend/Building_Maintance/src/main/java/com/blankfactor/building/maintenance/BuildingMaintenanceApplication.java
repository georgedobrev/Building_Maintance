package com.blankfactor.building.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BuildingMaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildingMaintenanceApplication.class, args);
	}

}
