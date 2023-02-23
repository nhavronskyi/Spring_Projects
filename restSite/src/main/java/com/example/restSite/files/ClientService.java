package com.example.restSite.files;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private final ClientRepository clientRepository;
	
	@Autowired
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public List<Client> getClients(){
		return clientRepository.findAll();
	}
	
	public void addClient(Client client) {
		List<Client> l = getClients();
		int counter = 0;
		for(Client i : l)
			if(i.getLogin().equals(client.getLogin()))
				counter++;
				
		if(counter == 0)
			clientRepository.save(client);
	}
	
	public void updateClientPass(Long id, String pass) {
		boolean b = clientRepository.existsById(id);
		if(b) {
			Client c = clientRepository.findById(id).get();
			c.setPass(pass);
			clientRepository.save(c);
		}
	}

	public void deleteClient(Long id) {
		boolean b = clientRepository.existsById(id);
		if(b) {
			clientRepository.deleteById(id);
		}
		
	}
}
