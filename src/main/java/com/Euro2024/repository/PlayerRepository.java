package com.Euro2024.repository;

import com.Euro2024.models.PlayerEntity;
import com.Euro2024.models.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository  extends JpaRepository<PlayerEntity,Integer> {
    List<PlayerEntity> findByTeam(TeamEntity team);
    PlayerEntity findByName(String name);
    List<PlayerEntity> findByDorsal(int number);
    List<PlayerEntity> findByPosition(PlayerEntity.Position position);
    List<PlayerEntity> findByGoals(int goals);
    List<PlayerEntity> findByYellowCard(int cards);
    List<PlayerEntity> findByRedCard(int cards);
    List<PlayerEntity> findByGoalkeeperSaves(int saves);
    List<PlayerEntity> findByAssists(int assists);
    List<PlayerEntity> findByBallsRecovered(int recovered);
    List<PlayerEntity> findByMinutesPlayed(int played);
    List<PlayerEntity> findByMatchesPlayed(int matches);
    //Order by Property
    List<PlayerEntity> findByOrderByGoalsDesc();
    List<PlayerEntity> findByOrderByAssistsDesc();
    List<PlayerEntity> findByOrderByDorsal();
    List<PlayerEntity> findByOrderByMatchesPlayedDesc();
    List<PlayerEntity> findByOrderByMinutesPlayedDesc();
    List<PlayerEntity> findByOrderByBallsRecoveredDesc();
    List<PlayerEntity> findByOrderByGoalkeeperSavesDesc();
    List<PlayerEntity> findByOrderByYellowCardDesc();
    List<PlayerEntity> findByOrderByRedCardDesc();


}
