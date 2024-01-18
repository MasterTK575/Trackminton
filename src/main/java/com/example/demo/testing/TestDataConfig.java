package com.example.demo.testing;

import com.example.demo.game.Game;
import com.example.demo.game.GameRepository;
import com.example.demo.gameset.GameSet;
import com.example.demo.gameset.GameSetRepository;
import com.example.demo.player.Player;
import com.example.demo.player.PlayerRepository;
import com.example.demo.team.Team;
import com.example.demo.team.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
public class TestDataConfig {
    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(PlayerRepository playerRepository, TeamRepository teamRepository,
                                              GameSetRepository gameSetRepository, GameRepository gameRepository) {
        return args -> {
            Player mariam = new Player("Mariam","Kramer","MK");
            Player will = new Player("Will","Smith","WS");
            Player tim = new Player("Tim","Krambeck","TK");
            Player nicolas = new Player("Nicolas","Wenzel","NW");
            playerRepository.saveAll(List.of(mariam,will,tim,nicolas));

            Team team1 = new Team("team1");
            team1.addTeamMember(tim);
            team1.addTeamMember(nicolas);
            Team team2 = new Team("team2");
            team2.addTeamMember(will);
            team2.addTeamMember(mariam);
            teamRepository.save(team1);
            teamRepository.save(team2);
            System.out.println(team1);
            System.out.println(team2);

            // GAMES
            Game game = new Game();
            GameSet set1 = new GameSet(15,21,game);
            GameSet set2 = new GameSet(21,15,game);
            GameSet set3 = new GameSet(20,22,game);
            gameSetRepository.saveAll(List.of(set1,set2,set3));


            game.addSet(set1);
            game.addSet(set2);
            game.addSet(set3);
            game.addTeam(team1);
            game.addTeam(team2);
            game.setWinner(team2);
            gameRepository.save(game);
            System.out.println(game);
        };
    }
}
