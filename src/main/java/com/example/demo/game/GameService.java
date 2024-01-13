package com.example.demo.game;

import com.example.demo.player.Player;
import com.example.demo.player.PlayerService;
import com.example.demo.team.Team;
import com.example.demo.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final TeamService teamService;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerService playerService, TeamService teamService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.teamService = teamService;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }



    public Game addNewGame(Game game) {
        // first add all Teams and their players to the database
        List<Team> teams = game.getTeams();
        for(Team team : teams) {
            for(Player player : team.getTeamMembers()) {

            }
            team.addGamePlayed(game);
            if(game.isWinner(team))team.addGameWon(game);
        }
        return null;
    }
}
