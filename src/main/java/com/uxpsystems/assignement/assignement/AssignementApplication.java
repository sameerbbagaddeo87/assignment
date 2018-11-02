package com.uxpsystems.assignement.assignement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.uxpsystems.assignement")
@ComponentScan("com.uxpsystems.assignement")
public class AssignementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignementApplication.class, args);
	}
}
