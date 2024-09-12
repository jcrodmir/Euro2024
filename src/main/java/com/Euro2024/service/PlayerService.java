package com.Euro2024.service;

import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.GenericResponse;
import com.Euro2024.models.PlayerEntity;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto playerDto,int teamId);
    List<PlayerDto> createPlayers(List<PlayerDto> playerDto,int teamId);
    //parameters is for the pagination
    GenericResponse<PlayerDto> getAllPlayer(int page, int pageSize);
    List<PlayerDto> getAllPlayersFromTeam(int teamId);
    List<PlayerDto> getAllPlayerFromTeam(int teamId, int id);
    PlayerDto getPlayerByName(String name);
    List<PlayerDto> getPlayerNumberByDorsal(int number);
    List<PlayerDto> getPlayerNumberByGoals(int goals);
    List<PlayerDto> getPlayerNumberYellowCards(int cards);
    List<PlayerDto> getPlayerNumberRedCards(int cards);
    List<PlayerDto> getPlayerNumberGoalKeeperSaves(int saves);
    List<PlayerDto> getPlayerNumberAssists(int assits);
    List<PlayerDto> getPlayerNumberRecovered(int recovered);
    List<PlayerDto> getPlayerNumberMinutes(int minutes);
    List<PlayerDto> getPlayerNumberMatches(int matches);
    List<PlayerDto> getPlayerByPosition(PlayerEntity.Position position);

    List<PlayerDto> getPlayerByYellowCard();
    List<PlayerDto> getPlayerByRedCard();
    List<PlayerDto> getPlayerByDorsal();
    List<PlayerDto> getPlayerByGoals();
    List<PlayerDto> getPlayerByGoalKeeperSaves();
    List<PlayerDto> getPlayerByAssists();
    List<PlayerDto> getPlayerByRecovered();
    List<PlayerDto> getPlayerByMinutes();
    List<PlayerDto> getPlayerByMatches();
    PlayerDto getPlayerById(int id);
    PlayerDto updatePlayer(PlayerDto playerDto, int id);
    void deletePlayer(int id);


}
