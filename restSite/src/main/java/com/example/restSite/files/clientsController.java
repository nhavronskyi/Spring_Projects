package com.example.restSite.files;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class clientsController {
	
	private final ClientService clientService;
	
	@Autowired
	public clientsController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}
	
	//GET
	@GetMapping("/getClients")
	public List<Client> getClients() {
		return clientService.getClients();
	}
	//POST
	@PostMapping("/addClient")
	public void addClent(@RequestParam(name = "login")String login,@RequestParam(name = "pass")String pass) {
		clientService.addClient(new Client(login, pass));
	}
	//PUT
	@PutMapping("/updateClient")
	public void updateClientPass(@RequestParam(value = "id")Long id,@RequestParam(name = "pass")String pass) {
		clientService.updateClientPass(id, pass);
	}
	//DELETE
	@DeleteMapping("/deleteCLient")
	public void deleteClient(@RequestParam(value = "id")Long id) {
		clientService.deleteClient(id);
	}
}