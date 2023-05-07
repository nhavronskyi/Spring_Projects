package com.example.pagination.controller;

import com.example.pagination.service.UserService;
import com.example.pagination.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService service;

    @PostMapping("/{num}")
    public void createUsers(@PathVariable int num) {
        service.createUsers(num);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.showAll();
    }


    @GetMapping("/{currentPage}/{elementsOnPage}")
    public List<User> getUsersWithPagination(@PathVariable int currentPage, @PathVariable int elementsOnPage) {
        return service.getUsersWithPagination(currentPage, elementsOnPage);
    }
}
