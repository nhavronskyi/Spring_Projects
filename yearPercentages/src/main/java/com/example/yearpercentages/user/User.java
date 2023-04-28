package com.example.yearpercentages.user;





import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public @Data
class User{
    @Id
    private long id;
    private String userName;
    private boolean isStarted;
}
