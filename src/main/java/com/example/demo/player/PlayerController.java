package com.example.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Player>> getPlayers() {
        return new ResponseEntity<>(playerService.getPlayers(), HttpStatus.OK);
    }

    @GetMapping(path = "{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable("playerId") Long playerId) {
        return new ResponseEntity<>(playerService.getPlayer(playerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> registerNewPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerService.addNewPlayer(player), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{playerId}")
    public ResponseEntity<Player> deletePlayer(@PathVariable("playerId") Long playerId) {
        return new ResponseEntity<>(playerService.deletePlayer(playerId),HttpStatus.OK);
    }

    @PutMapping(path = "{playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("playerId") Long playerId,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String userName){
        return new ResponseEntity<>(playerService.updatePlayer(playerId,firstName,lastName,userName),HttpStatus.OK);
    }
}
