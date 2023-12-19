package com.example.demo.gameset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSetService {

    private final GameSetRepository gameSetRepository;

    @Autowired
    public GameSetService(GameSetRepository gameSetRepository) {
        this.gameSetRepository = gameSetRepository;
    }

    public List<GameSet> getSets() {
        return gameSetRepository.findAll();
    }
}
