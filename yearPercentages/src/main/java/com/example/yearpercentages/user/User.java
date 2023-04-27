package com.example.yearpercentages.user;


//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Entity
//@Table(name = "user")
public class User{
    //@Id
    private long id;
    private String userName;
    private boolean isStarted;
}
