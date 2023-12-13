package com.example.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @GetMapping(path = "{playerId}")
    public Player getPlayer(@PathVariable("playerId") Long playerId) {
        return playerService.getPlayer(playerId);
    }

    @PostMapping
    public Player registerNewPlayer(@RequestBody Player player) {
        return playerService.addNewPlayer(player);
    }

    @DeleteMapping(path = "{playerId}")
    public void deletePlayer(@PathVariable("playerId") Long playerId) {
        playerService.deletePlayer(playerId);
    }

    @PutMapping(path = "{playerId}")
    public Player updatePlayer(@PathVariable("playerId") Long playerId,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String userName){
        return playerService.updatePlayer(playerId,firstName,lastName,userName);
    }
}
