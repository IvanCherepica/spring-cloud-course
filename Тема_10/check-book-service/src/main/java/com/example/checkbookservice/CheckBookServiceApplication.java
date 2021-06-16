package com.example.checkbookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@SpringBootApplication
@EnableBinding(Processor.class)
public class CheckBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckBookServiceApplication.class, args);
	}

}
