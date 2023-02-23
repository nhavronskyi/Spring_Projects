package com.example.mySite.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
	
	private final ClientService clientService;
	
	@GetMapping("/hello")
	public String hello() {
		return "index";
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
			@RequestParam(name = "login")String login, 
			@RequestParam(name = "pass")String pass) {
		clientService.addClient(new Client(login, pass));
	}
	
	
	@DeleteMapping("/deleteClient")
	public void deleteClient(@RequestParam(name = "id") Long id) {
		clientService.deleteClient(id);
	}
}
