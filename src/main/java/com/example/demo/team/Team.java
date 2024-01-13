package com.example.demo.team;

import com.example.demo.player.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import com.example.demo.game.Game;

import java.util.*;

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
    private final Set<Player> teamMembers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private final Set<Game> gamesPlayed = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "winner")
    private final Set<Game> wonGames = new HashSet<>();

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

    public Set<Player> getTeamMembers() {
        return teamMembers;
    }


    // this gets called during initialization of the team object
    public void setTeamMembers(Set<Player> teamMembers) {
        for(Player player : teamMembers){
            this.teamMembers.add(player);
            player.addTeam(this);
        }
    }

    // for testing
    public void addTeamMember(Player player) {
        this.teamMembers.add(player);
        player.addTeam(this);
    }

    public void addGamePlayed(Game game) {
        this.gamesPlayed.add(game);
    }

    public void addGameWon(Game game) {
        this.wonGames.add(game);
    }


    public Set<Game> getGamesPlayed() {
        return gamesPlayed;
    }

    public Set<Game> getWonGames() {
        return wonGames;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamMembers=" + teamMembers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(teamMembers, team.teamMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teamMembers);
    }
}
