package com.example.demo.player;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // @Query("SELECT s FROM Player s WHERE s.userName = ?1")
    // this would be another option

    // custom method to find a player by their username
    // == SELECT * FROM players WHERE userName = ?
    Optional<Player> findPlayerByUserName(String userName);

}
