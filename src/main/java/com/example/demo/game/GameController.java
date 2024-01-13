package com.example.demo.game;

import com.example.demo.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        return new ResponseEntity<>(gameService.getGames(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Game> addNewGame(@RequestBody Game game) {
        return new ResponseEntity<>(gameService.addNewGame(game), HttpStatus.CREATED);
    }
}
