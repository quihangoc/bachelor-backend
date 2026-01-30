package com.example.bachelor.backend.controller;

import com.example.bachelor.backend.model.Player;
import com.example.bachelor.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable Long id){
        Player player = playerService.getPlayer(id);
        if(player == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }
}
