package com.example.restSite.files;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class clientsController {
	
	@GetMapping("/")
	public String hello() {
		return "hello";
	}
}
