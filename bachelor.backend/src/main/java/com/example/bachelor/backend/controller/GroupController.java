package com.example.bachelor.backend.controller;

import com.example.bachelor.backend.model.Group;
import com.example.bachelor.backend.model.Player;
import com.example.bachelor.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody Group group){
        Group createdGroup = groupService.createGroup(group);
        if(createdGroup == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdGroup);
    }

    @GetMapping("/getGroupScore/{id}")
    public ResponseEntity<?> getGroupScore(@PathVariable Long id){
        int groupScore = groupService.getGroupScore(id);
        return ResponseEntity.ok(groupScore);

    }

    @GetMapping("/getPlayerList/{id}")
    public ResponseEntity<?> getPlayerList(@PathVariable Long id){
        List<Player> playerList = groupService.getPlayersOfGroup(id);
        if(playerList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playerList);
    }

    @GetMapping("/getFirstRanked/{id}")
    public ResponseEntity<?> getFirstRankedPlayer(@PathVariable Long id){
        Player player = groupService.getFirstRankedPlayer(id);
        if(player == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }

    @GetMapping("/getGroupAverage/{id}")
    public ResponseEntity<?> getAverageGroupScore(@PathVariable Long id){
        double averageGroupScore = groupService.getAverageGroupScore(id);
        return ResponseEntity.ok(averageGroupScore);
    }
}
