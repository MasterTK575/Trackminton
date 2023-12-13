package com.example.demo.team;

import com.example.demo.player.Player;
import jakarta.persistence.*;
import com.example.demo.game.Game;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany
//    @JoinTable(
//            name = "team_players",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "player_id")
//    )
//    private Set<Player> teamMembers = new HashSet<>();

    @ManyToMany(mappedBy = "teams")
    private final Set<Game> gamesPlayed = new HashSet<>();

    @OneToMany(mappedBy = "winner")
    private final Set<Game> wonGames = new HashSet<>();

    @OneToMany(mappedBy = "firstServe")
    private final Set<Game> firstServeGames = new HashSet<>();

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

//    public Set<Player> getTeamMembers() {
//        return teamMembers;
//    }
}
