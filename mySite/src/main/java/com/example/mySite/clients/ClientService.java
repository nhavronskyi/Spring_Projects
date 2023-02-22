package com.example.mySite.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	private final ClientRepository clientsRepository;

	@Autowired
	public ClientService(ClientRepository clientsRepository) {
		this.clientsRepository = clientsRepository;
	}
	
	public List<Client> getClients(){
		return clientsRepository.findAll();
	}

	public void addClient(Client client) {
		clientsRepository.save(client);
	}

	public void deleteClient(Long id) {
		boolean exist = clientsRepository.existsById(id);
		if(!exist)
			throw new IllegalStateException("student with id: " + id + " does not exist");
		else 
			clientsRepository.deleteById(id);
	}
}
