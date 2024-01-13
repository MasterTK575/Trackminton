package com.example.demo.game;

import com.example.demo.gameset.GameSet;
import com.example.demo.team.Team;
import jakarta.persistence.*;

import java.util.*;

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
    private final Set<Team> teams = new HashSet<>();

    //@JsonIgnore
    @OneToMany(mappedBy = "game")
    private Set<GameSet> gameSets = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Team winner;

    public Game() {
    }

    public Game(Set<GameSet> gameSets, Team winner) {
        this.gameSets = gameSets;
        this.winner = winner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        System.out.println("setTeams gets called");
        for(Team team : teams) {
            this.teams.add(team);
            team.addGamePlayed(this);
        }
    }

    // for testing only
    public void addTeam(Team team) {
        this.teams.add(team);
        team.addGamePlayed(this);
    }

    public void setGameSets(Set<GameSet> gameSets) {
        System.out.println("setGameSets gets called");
        for(GameSet gameSet : gameSets) {
            this.gameSets.add(gameSet);
            gameSet.setGame(this);
        }
    }

    public Set<GameSet> getGameSets() {
        return gameSets;
    }


    // only for testing
    public void addSet(GameSet set) {
        this.gameSets.add(set);
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
                ", sets=" + gameSets +
                ", winner=" + winner +
                '}';
    }
}
