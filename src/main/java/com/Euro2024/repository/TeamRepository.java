package com.Euro2024.repository;

import com.Euro2024.models.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity,Integer> {
}
