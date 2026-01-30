package com.example.bachelor.backend.service;

import com.example.bachelor.backend.model.Player;
import com.example.bachelor.backend.repo.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayer(Long id){
        return playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Player mit ID " + id + " nicht gefunden"));
    }
}
