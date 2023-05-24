package com.develhope.redis.entities.jpa;

import com.develhope.redis.entities.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserJPA extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public UserJPA(Long id, String firstName, String lastName, String profilePicture, String email, String encryptedPassword, String address, String city, int domicileNumber, String state, String fiscalCode) {
        this.id = id;
    }
   public UserJPA(){}

}
