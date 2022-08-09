package com.module.usermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UsermoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermoduleApplication.class, args);
	}

}
