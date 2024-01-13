package com.example.demo.game;

import com.example.demo.gameset.GameSetRepository;
import com.example.demo.player.Player;
import com.example.demo.player.PlayerService;
import com.example.demo.team.Team;
import com.example.demo.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameSetRepository gameSetRepository;
    private final TeamService teamService;

    @Autowired
    public GameService(GameRepository gameRepository, GameSetRepository gameSetRepository, TeamService teamService) {
        this.gameRepository = gameRepository;
        this.gameSetRepository = gameSetRepository;
        this.teamService = teamService;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }


    @Transactional
    public Game addNewGame(Game game) {
        // first add all Teams and their players to the database
        for(Team team : game.getTeams()) {
            teamService.addNewTeam(team);
        }
        gameSetRepository.saveAll(game.getGameSets());
        gameRepository.save(game);
        return game;
    }
}
