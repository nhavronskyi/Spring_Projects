package com.example.mySite.clients;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClientService {
	private final ClientsRepository clientsRepository;
	
	public ClientService(ClientsRepository clientsRepository) {
		this.clientsRepository = clientsRepository;
	}
	
	public List<Clients> getClients(){
		return clientsRepository.findAll();
	}
}
