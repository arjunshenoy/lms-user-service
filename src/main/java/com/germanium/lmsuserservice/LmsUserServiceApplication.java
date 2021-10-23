package com.germanium.lmsuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.germanium.lmsuserservice.*")
@EntityScan("com.germanium.lmsuserservice.model")
@EnableJpaRepositories
@EnableJpaAuditing
@ComponentScan(basePackages = { "com.germanium.lmsuserservice.*" })
public class LmsUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsUserServiceApplication.class, args);
	}

}
