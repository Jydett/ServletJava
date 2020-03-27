package fr.polytech.jydet.td5.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    private String username;
    private String password;

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }
}
