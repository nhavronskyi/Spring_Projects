package com.example.mySite.clients; 

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(ClientsRepository clientsRepository) {
		return args -> {
			Clients mike = new Clients("mike", "232");
			Clients alex = new Clients("alex", "202");
			clientsRepository.saveAll(List.of(mike, alex));
		};
	}
}
