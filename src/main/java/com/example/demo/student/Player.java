package com.example.demo.student;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    // constructors
    public Player() {
    }
    public Player(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // toString
    @Override
    public String toString() {
        return String.format("Player{id=%d, name=%s %s}",id,firstName,lastName);
    }
}
