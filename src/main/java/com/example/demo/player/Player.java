package com.example.demo.player;

import jakarta.persistence.*;

@Entity
@Table
public class Player {
    @Id
    @SequenceGenerator(name = "player_sequence",
            sequenceName = "player_sequence",allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private long id;
    private String firstName;
    private String lastName;
    private String userName;

    // constructors
    public Player() {
    }
    public Player(long id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }
    public Player(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // toString
    @Override
    public String toString() {
        return String.format("Player{id=%d, name=%s %s, username: %s}",id,firstName,lastName,userName);
    }
}
