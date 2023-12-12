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
            Player mariam = new Player("Mariam","Kramer","MK");
            Player tim = new Player("Tim","Krambeck","TK");
            Player nicolas = new Player("Nicolas","Wenzel","NW");

            repository.saveAll(List.of(mariam,tim,nicolas));
        };
    }
}
