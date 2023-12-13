package com.example.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    public Player getPlayer(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Player with id %d doesn't exist.",playerId)));
    }

    public Player addNewPlayer(Player player) {
        Optional<Player> playerByUserName = playerRepository.findPlayerByUserName(player.getUserName());
        if(playerByUserName.isPresent()) { // to make sure that usernames are unique
            throw new IllegalStateException("username already taken");
        }

        return playerRepository.save(player);
    }

    public Player deletePlayer(Long playerId) {
        Player playerToDelete = playerRepository.findById(playerId).orElseThrow(() -> new UserNotFoundException(String.format("Player with id %d doesn't exist.",playerId)));
        playerRepository.delete(playerToDelete);
        return playerToDelete;
    }

    @Transactional
    public Player updatePlayer(Long playerId, String firstName, String lastName, String userName) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Player with id %d doesn't exist.",playerId)));

        //update first name
        if(firstName!= null && !firstName.isEmpty() &&
            !Objects.equals(player.getFirstName(),firstName)) {
            player.setFirstName(firstName);
        }
        //update last name
        if(lastName!= null && !lastName.isEmpty() &&
                !Objects.equals(player.getLastName(),lastName)) {
            player.setLastName(lastName);
        }

        //update username
        if(userName!= null && !userName.isEmpty() &&
                !Objects.equals(player.getUserName(),userName)) {

            Optional<Player> playerByUserName = playerRepository.findPlayerByUserName(userName);
            if(playerByUserName.isPresent()) { // to make sure that usernames are unique
                throw new IllegalStateException("username already taken");
            }
            player.setUserName(userName);
        }
        return player;
    }


}
