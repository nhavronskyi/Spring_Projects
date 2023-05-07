package com.example.pagination.service;

import com.example.pagination.dao.UserDao;
import com.example.pagination.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserDao userDao;

    public void createUsers(int num){
        userDao.deleteAll();
        for (int i = 1; i <= num; i++)
            userDao.save(new User((long)i, Character.toString(i+64),Character.toString(i+64)));
    }

    public List<User> showAll(){
        return userDao.findAll();
    }

    public List<User> getUsersWithPagination(int elementsOnPage, int currentPage){
        return userDao.findAll(PageRequest.of(elementsOnPage, currentPage)).stream().toList();
    }
}
