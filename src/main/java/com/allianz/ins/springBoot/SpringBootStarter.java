package com.allianz.ins.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.allianz.ins"})
@EnableJpaRepositories(basePackages={"com.allianz.ins.repository"})
@EntityScan("com.allianz.ins") 
public class SpringBootStarter {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarter.class, args);
	}

}
