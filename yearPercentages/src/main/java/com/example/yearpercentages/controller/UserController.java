package com.example.yearpercentages.controller;


import com.example.yearpercentages.component.Bot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Bot bot;

    public UserController(Bot bot) {
        this.bot = bot;
    }

    @GetMapping
    public Map<Long, String> getUsers(){
        return bot.getMap();
    }

}
