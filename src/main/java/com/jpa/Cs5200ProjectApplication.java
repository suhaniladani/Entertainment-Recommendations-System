package com.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Cs5200ProjectApplication extends SpringBootServletInitializer {

	@Override
	 protected SpringApplicationBuilder
	  configure(SpringApplicationBuilder application) {
	  return application.sources(Cs5200ProjectApplication.class);
	 }
	
	public static void main(String[] args) {
		SpringApplication.run(Cs5200ProjectApplication.class, args);
	}
}
