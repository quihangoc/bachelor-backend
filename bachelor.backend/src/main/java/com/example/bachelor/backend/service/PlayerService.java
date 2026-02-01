package com.example.bachelor.backend.service;

import com.example.bachelor.backend.model.Player;
import com.example.bachelor.backend.repo.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(Player player){
        if(player != null){
            Player savedPlayer = playerRepository.save(player);
            return savedPlayer;
        }
        return null;
    }
    public Player getPlayer(Long id){
        return playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Player mit ID " + id + " nicht gefunden"));
    }

    public Player editPlayer(Player player){
        Player editedPlayer = playerRepository.findById(player.getId()).orElseThrow(() -> new EntityNotFoundException(
                "Player mit ID " + player.getId() + " nicht gefunden"));
        editedPlayer.setName(player.getName());
        editedPlayer.setScore(player.getScore());
        playerRepository.save(editedPlayer);
        return editedPlayer;
    }

    public List<Player> getRankedList() {
        List<Player> playerList = playerRepository.findAll();

        for (int i = 0; i < playerList.size(); i++) {
            for (int j = 0; j < playerList.size() - 1 - i; j++) {
                Player p1 = playerList.get(j);
                Player p2 = playerList.get(j + 1);

                if (p1.getScore() < p2.getScore()) {
                    playerList.set(j, p2);
                    playerList.set(j + 1, p1);
                }
            }
        }

        return playerList;
    }


}
