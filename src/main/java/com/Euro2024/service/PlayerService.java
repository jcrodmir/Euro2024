package com.Euro2024.service;

import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.GenericResponse;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto playerDto,int teamId);
    //parameters is for the pagination
    GenericResponse<PlayerDto> getAllPlayer(int page, int pageSize);
    List<PlayerDto> getAllPlayersFromTeam(int teamId);
    List<PlayerDto> getAllPlayerFromTeam(int teamId, int id);
    PlayerDto getPlayerById(int id);
    PlayerDto updatePlayer(PlayerDto playerDto, int id);
    void deletePlayer(int id);
}
