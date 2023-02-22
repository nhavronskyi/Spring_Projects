package com.example.mySite.clients; 

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(ClientRepository clientsRepository) {
		return args -> {
			Client defaultU = new Client("default", "000");
			clientsRepository.save(defaultU);
		};
	}
}
