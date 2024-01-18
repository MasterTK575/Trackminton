package com.example.demo.gameset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/sets")
public class GameSetController {
    private final GameSetService gameSetService;

    @Autowired
    public GameSetController(GameSetService gameSetService) {
        this.gameSetService = gameSetService;
    }

    @GetMapping
    public ResponseEntity<List<GameSet>> getSets() {
        return new ResponseEntity<>(gameSetService.getSets(), HttpStatus.OK);
    }
}
