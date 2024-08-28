package com.Euro2024.repository;

import com.Euro2024.dto.PlayerDto;
import com.Euro2024.models.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity,Integer> {

    TeamEntity findByCountry(String country);
    TeamEntity findByFederation(String federation);
    TeamEntity findByCoach(String coach);
    TeamEntity findByChampionships(int champions);
    TeamEntity findByFoundation(int year);


}
