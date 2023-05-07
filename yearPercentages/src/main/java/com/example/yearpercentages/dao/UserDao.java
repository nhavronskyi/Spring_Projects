package com.example.yearpercentages.dao;


import com.example.yearpercentages.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findAllByIsStartedIsTrue();
}