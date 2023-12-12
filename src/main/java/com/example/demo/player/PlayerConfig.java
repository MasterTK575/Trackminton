package com.example.demo.player;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PlayerConfig {
    @Bean
    CommandLineRunner commandLineRunner(PlayerRepository repository) {
        return args -> {
            Player mariam = new Player("Mariam","Kramer");
            Player tim = new Player("Tim","Krambeck");
            Player nicolas = new Player("Nicolas","Wenzel");

            repository.saveAll(List.of(mariam,tim,nicolas));
        };
    }
}
