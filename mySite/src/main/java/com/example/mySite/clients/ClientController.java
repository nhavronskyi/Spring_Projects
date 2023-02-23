package com.example.mySite.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ClientController {
	
	private final ClientService clientService;
	
	@GetMapping("/mySite")
	public String hello() {
		return "index";
	}
	
	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping("/getClients")
	public String getClients(Model model){
		List<Client> list = clientService.getClients();
			model.addAttribute("clients", list);		
		return "index";
	}
	
	@PostMapping("/createClient")
	public String createClient(
			@RequestParam(name = "login")String login, 
			@RequestParam(name = "pass")String pass, Model model) {
		List<Client> list = clientService.getClients();
		for(Client l : list)
			if(l.getLogin().equals(login)) {
				String s = login + " already exist";
				model.addAttribute("client", s);
				return "index";
			}
		clientService.addClient(new Client(login, pass));
		return "index";
	}
}
