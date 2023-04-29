package com.example.yearpercentages.dao;


import com.example.yearpercentages.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("update User u set u.isStarted = ?1 where u.id = ?2")
    void update(boolean isStarted, long id);
}
