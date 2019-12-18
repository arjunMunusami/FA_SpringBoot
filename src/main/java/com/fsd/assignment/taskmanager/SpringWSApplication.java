package com.fsd.assignment.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class SpringWSApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringWSApplication.class, args);
	}

}
