package com.example.bachelor.backend.controller;

import com.example.bachelor.backend.model.Player;
import com.example.bachelor.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<?> createPlayer(@RequestBody Player player){
        Player createdPlayer = playerService.createPlayer(player);
        if(createdPlayer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable Long id){
        Player player = playerService.getPlayer(id);
        if(player == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }

    @PatchMapping("/patchPlayer")
    public ResponseEntity<?> editPlayer(@RequestBody Player player){
        Player editedPlayer = playerService.editPlayer(player);
        return ResponseEntity.ok(editedPlayer);
    }

    @GetMapping("/getRanked")
    public ResponseEntity<?> getRankedList(){
        List<Player> rankedList = playerService.getRankedList();
        return ResponseEntity.ok(rankedList);
    }

    @GetMapping("/getSorted")
    public ResponseEntity<?> getSortedList(){
        List<Player> sortedList = playerService.getSortedList();
        return ResponseEntity.ok(sortedList);
    }
}
