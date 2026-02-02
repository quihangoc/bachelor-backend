package com.example.bachelor.backend.controller;

import com.example.bachelor.backend.model.Group;
import com.example.bachelor.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
