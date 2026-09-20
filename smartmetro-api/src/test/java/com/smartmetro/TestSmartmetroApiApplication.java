package com.smartmetro;

import org.springframework.boot.SpringApplication;

public class TestSmartmetroApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(SmartmetroApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
