package com.example.mySite.helloWorld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	@RequestMapping("/smth")
	public String hello() {
		return "hello world";
	}
}



