package com.example.demo.game;

import com.example.demo.gameset.GameSet;
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
        // determine the winner based on the sets
        game.setWinner(determineWinner(game));
        // first add all Teams and their players to the database
        for(Team team : game.getTeams()) {
            teamService.addNewTeam(team);
        }
        gameSetRepository.saveAll(game.getGameSets());

        gameRepository.save(game);
        return game;
    }

    private Team determineWinner(Game game) {
        int team1SetsWon = 0;
        int team2SetsWon = 0;
        for(GameSet gameSet : game.getGameSets()) {
            if(gameSet.getScoreTeam1() > gameSet.getScoreTeam2())team1SetsWon++;
            else if (gameSet.getScoreTeam2() > gameSet.getScoreTeam1()) team2SetsWon++;
            else throw new IllegalStateException("There must be a winner for a set");
        }
        // error check
        if(team1SetsWon==team2SetsWon)throw new IllegalStateException("Team1 and Team2 have won the same amount of sets");
        return (team1SetsWon>team2SetsWon) ? game.getTeams().get(0) : game.getTeams().get(1);
    }

}
