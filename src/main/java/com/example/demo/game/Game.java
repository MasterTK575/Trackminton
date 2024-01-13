package com.example.demo.game;

import com.example.demo.gameset.GameSet;
import com.example.demo.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "game_teams",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private final List<Team> teams = new ArrayList<>();

    //@JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<GameSet> sets = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Team winner;

    public Game() {
    }

    public Game(List<GameSet> sets, Team winner) {
        this.sets = sets;
        this.winner = winner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
        team.addGamePlayed(this);
    }
    public List<GameSet> getSets() {
        return sets;
    }

    public void addSet(GameSet set) {
        this.sets.add(set);
        set.setGame(this);
    }

    public Team getWinner() {
        return winner;
    }

    public boolean isWinner(Team team) {
        return Objects.equals(team,winner);
    }

    public void setWinner(Team team) {
        this.winner = team;
        team.addGameWon(this);
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", teams=" + teams +
                ", sets=" + sets +
                ", winner=" + winner +
                '}';
    }
}
