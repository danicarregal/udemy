package com.carregal.springbootlockdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.carregal.springbootlockdown", "com.carregal.other" })
public class SpringbootlockdownApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootlockdownApplication.class, args);
	}

}
