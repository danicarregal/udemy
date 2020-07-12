package com.spring.carregal.InitializrProject.conf;

import java.util.Calendar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {

	@Bean("calendario")
	public Calendar getCurrentCalendar() {

		return Calendar.getInstance();
	}

}
