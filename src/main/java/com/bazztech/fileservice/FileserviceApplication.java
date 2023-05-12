package com.bazztech.fileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class FileserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FileserviceApplication.class, args);
	}

}
