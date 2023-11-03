package com.example.infofusionback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.infofusionback")
public class InfoFusionBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoFusionBackApplication.class, args);
	}

}
