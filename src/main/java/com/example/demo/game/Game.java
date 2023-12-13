package com.example.demo.game;

import com.example.demo.gameset.GameSet;
import com.example.demo.team.Team;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "game_teams",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private final Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "game")
    private final Set<GameSet> sets = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Team winner;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_serve_id", referencedColumnName = "id")
    private Team firstServe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Team getFirstServe() {
        return firstServe;
    }

    public void setFirstServe(Team firstServe) {
        this.firstServe = firstServe;
    }
}
