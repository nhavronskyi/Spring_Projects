package com.example.yearpercentages.user;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public @Data
class User{
    @Id
    private long id;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "isStarted", nullable = false)
    private boolean isStarted;
}
