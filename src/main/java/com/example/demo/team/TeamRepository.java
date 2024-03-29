package com.example.demo.team;

import com.example.demo.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    Optional<Team> findTeamByName(String name);
}
