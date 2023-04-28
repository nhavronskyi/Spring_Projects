package com.example.yearpercentages.repository;


import com.example.yearpercentages.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
