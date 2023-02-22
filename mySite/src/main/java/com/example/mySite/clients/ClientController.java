package com.example.mySite.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
	
	private final ClientService clientService;
	
	@RequestMapping("/hello")
	public String showHello() {
		return "Hello client";
	}
	
	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping("/getClients")
	public List<Client> getClients(){
		return clientService.getClients();
	}
	
	
	@PostMapping("/createClient")
	public void createClient(
			@RequestParam(name = "name")String name, 
			@RequestParam(name = "pass")String pass) {
		clientService.addClient(new Client(name, pass));
	}
	
	
	@DeleteMapping("/deleteClient")
	public void deleteClient(@RequestParam(name = "id") Long id) {
		clientService.deleteClient(id);
	}
}
