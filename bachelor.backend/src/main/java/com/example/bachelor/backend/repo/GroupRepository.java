package com.example.bachelor.backend.repo;

import com.example.bachelor.backend.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
