package com.example.yearpercentages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.yearpercentages.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
