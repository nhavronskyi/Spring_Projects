package com.example.mySite.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientsController {
	
	@RequestMapping("/hello")
	public String showHello() {
		return "Hello client";
	}
	
	
	
	private final ClientService clientService;
	
	@Autowired
	public ClientsController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping("/getClients")
	public List<Clients> getClients(){
		return clientService.getClients();
	}
	
	
}
