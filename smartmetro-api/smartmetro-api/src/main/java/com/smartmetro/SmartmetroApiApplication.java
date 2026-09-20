package com.smartmetro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class SmartmetroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartmetroApiApplication.class, args);
		log.info("SmartmetroApiApplication started");
	}
}
