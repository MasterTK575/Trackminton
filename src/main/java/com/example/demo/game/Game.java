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
    private final List<Team> teams = new ArrayList<>();

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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        if(teams.size()!=2)throw new IllegalStateException("Game must have exactly 2 teams");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(teams, game.teams) && Objects.equals(gameSets, game.gameSets) && Objects.equals(winner, game.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teams, gameSets, winner);
    }
}
