package com.example.yearpercentages.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "chat_id")
    private long id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "is_started", nullable = false)
    private boolean isStarted;
}

