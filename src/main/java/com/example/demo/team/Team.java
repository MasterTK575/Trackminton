package com.example.demo.team;

import com.example.demo.player.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.demo.game.Game;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE,
        fetch = FetchType.EAGER)
    @JoinTable(
            name = "team_players",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> teamMembers = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private final List<Game> gamesPlayed = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "winner")
    private final List<Game> wonGames = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "firstServe")
    private final List<Game> firstServeGames = new ArrayList<>();

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getTeamMembers() {
        return teamMembers;
    }

    public void addPlayer(Player player) {
        this.teamMembers.add(player);
        player.addTeam(this);
    }

    public void addGamePlayed(Game game) {
        this.gamesPlayed.add(game);
    }

    public void addGameWon(Game game) {
        this.wonGames.add(game);
    }

    public void addFirstServeGame(Game game) {
        this.firstServeGames.add(game);
    }


    public List<Game> getGamesPlayed() {
        return gamesPlayed;
    }

    public List<Game> getWonGames() {
        return wonGames;
    }

    public List<Game> getFirstServeGames() {
        return firstServeGames;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamMembers=" + teamMembers +
                '}';
    }
}
