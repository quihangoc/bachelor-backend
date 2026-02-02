package com.example.bachelor.backend.service;

import com.example.bachelor.backend.model.Group;
import com.example.bachelor.backend.model.Player;
import com.example.bachelor.backend.repo.GroupRepository;
import com.example.bachelor.backend.repo.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(Group group){
        if(group != null){
            return groupRepository.save(group);
        }
        return null;
    }

    public int getGroupScore(Long id){
        int score = 0;
        Group group = groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Group mit ID " + id + " nicht gefunden"));
        if(group.getPlayerlist().isEmpty()){
            return 0;
        }

        score = calculateGroupScore(group);
        return score;
    }

    public List<Group> getRankedList() {
        List<Group> groupList = groupRepository.findAll();

        for (int i = 0; i < groupList.size(); i++) {
            for (int j = 0; j < groupList.size() - 1 - i; j++) {

                Group g1 = groupList.get(j);
                Group g2 = groupList.get(j + 1);

                int scoreG1 = calculateGroupScore(g1);
                int scoreG2 = calculateGroupScore(g2);

                if (scoreG1 < scoreG2) {
                    groupList.set(j, g2);
                    groupList.set(j + 1, g1);
                }
            }
        }
        return groupList;
    }

    public List<Player> getPlayerOfGroup(Long id){
        Group group = groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Group mit ID " + id + " nicht gefunden"));

        if(group.getPlayerlist().isEmpty()){
            return null;
        }
        return group.getPlayerlist();
    }

    public Player getFirstRankedPlayer(Long id ){
        Group group = groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Group mit ID " + id + " nicht gefunden"));

        List<Player> playerList = group.getPlayerlist();
        if(playerList.isEmpty()){
            return null;
        }

        playerList = sortGroupRanking(playerList);

        return playerList.get(0);
    }

    public double getAverageGroupScoreBuggy(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Group mit ID " + groupId + " nicht gefunden"));

        List<Player> players = group.getPlayerlist();

        if (players.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (int i = 0; i < players.size(); i++) {
            sum = players.get(i).getScore();
        }

        return sum / players.size();
    }



    private int calculateGroupScore(Group group){
        int score = 0;
        for(Player player : group.getPlayerlist()){
            score = score + player.getScore();
        }
        return score;
    }

    private List<Player> sortGroupRanking(List<Player> playerList){
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
