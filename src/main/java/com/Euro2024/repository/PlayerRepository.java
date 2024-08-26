package com.Euro2024.repository;

import com.Euro2024.models.PlayerEntity;
import com.Euro2024.models.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository  extends JpaRepository<PlayerEntity,Integer> {
    List<PlayerEntity> findByTeam(TeamEntity team);
}
