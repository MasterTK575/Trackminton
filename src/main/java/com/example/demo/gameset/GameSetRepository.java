package com.example.demo.gameset;

import com.example.demo.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSetRepository extends JpaRepository<GameSet,Long> {
}
