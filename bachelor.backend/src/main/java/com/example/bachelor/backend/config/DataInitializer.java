package com.example.bachelor.backend.config;

import com.example.bachelor.backend.model.Group;
import com.example.bachelor.backend.model.Player;
import com.example.bachelor.backend.repo.GroupRepository;
import com.example.bachelor.backend.repo.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(PlayerRepository playerRepository,
                               GroupRepository groupRepository) {
        return args -> {

            if (playerRepository.count() == 0 && groupRepository.count() == 0) {

                Group groupA = new Group(null, "Team Alpha", new ArrayList<>());
                Group groupB = new Group(null, "Team Beta", new ArrayList<>());

                groupRepository.save(groupA);
                groupRepository.save(groupB);

                Player p1 = new Player(null, "Alice", 120, new ArrayList<>());
                Player p2 = new Player(null, "Bob", 80, new ArrayList<>());
                Player p3 = new Player(null, "Charlie", 150, new ArrayList<>());
                Player p4 = new Player(null, "Diana", 90, new ArrayList<>());

                p1.setGroupList(List.of(groupA));
                p2.setGroupList(List.of(groupA));

                p3.setGroupList(List.of(groupB));
                p4.setGroupList(List.of(groupB));

                groupA.setPlayerlist(List.of(p1, p2));
                groupB.setPlayerlist(List.of(p3, p4));

                playerRepository.save(p1);
                playerRepository.save(p2);
                playerRepository.save(p3);
                playerRepository.save(p4);

                groupRepository.save(groupA);
                groupRepository.save(groupB);

                System.out.println("Demo-Daten für Player & Groups wurden erstellt!");
            }
        };
    }
}
