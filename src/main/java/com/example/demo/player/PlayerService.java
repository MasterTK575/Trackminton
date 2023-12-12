package com.example.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public void addNewPlayer(Player player) {
        Optional<Player> playerByUserName = playerRepository.findPlayerByUserName(player.getUserName());
        if(playerByUserName.isPresent()) { // to make sure that usernames are unique
            throw new IllegalStateException("userName taken");
        }

        playerRepository.save(player);
    }
}
