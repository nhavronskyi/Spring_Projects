package com.example.mySite.clients; 

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(ClientsRepository clientsRepository) {
		return args -> {
			Clients defaultU = new Clients("default", "000");
			clientsRepository.save(defaultU);
		};
	}
}
