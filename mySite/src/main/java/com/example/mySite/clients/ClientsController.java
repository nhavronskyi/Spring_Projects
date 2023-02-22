package com.example.mySite.clients;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientsController {
	
	@RequestMapping("/hello")
	public String showHello() {
		return "Hello client";
	}
}
