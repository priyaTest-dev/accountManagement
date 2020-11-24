package com.management.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan({ "com.management.account" })
public class SpringBootAccountApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootAccountApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Entering SpringBootAccountApplication");
		SpringApplication.run(SpringBootAccountApplication.class, args);

	}

}
