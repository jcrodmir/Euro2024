package com.Euro2024.service;

import com.Euro2024.dto.PlayerDto;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto playerDto);
    //parameters is for the pagination
    <List> java.util.List<PlayerDto> getAllPlayer(int page, int pageSize);
    PlayerDto getPlayerById(int id);
    PlayerDto updatePlayer(PlayerDto playerDto, int id);
    void deletePlayer(int id);
}
