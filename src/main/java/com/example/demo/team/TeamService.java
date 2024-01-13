package com.example.demo.team;

import com.example.demo.player.Player;
import com.example.demo.player.PlayerRepository;
import com.example.demo.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    private final PlayerRepository playerRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public List<Team> getTeams() {return teamRepository.findAll();}

    @Transactional
    public Team addNewTeam(Team team) {
        playerRepository.saveAll(team.getTeamMembers());
        teamRepository.save(team);
        return team;
    }
}
