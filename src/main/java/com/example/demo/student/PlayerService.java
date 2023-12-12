package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class PlayerService {

    public List<Player> getPlayers() {
        return List.of( new Player(1L,"Mariam","Kramer"));
    }
}
