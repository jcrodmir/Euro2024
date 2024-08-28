package com.Euro2024.service;

import com.Euro2024.dto.GenericResponse;
import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.TeamDto;
import com.Euro2024.models.TeamEntity;

import java.util.List;

public interface TeamService {
    TeamDto createTeam(TeamDto teamDto);
    //parameters is for the pagination
    GenericResponse<TeamDto> getAllTeam(int page, int pageSize);
    TeamDto getTeamById(int id);
    //Personalizados completo
    List<PlayerDto> getPlayersByTeamId(int id);
    TeamDto getByCountry(String country);
    TeamDto getByFederation(String federation);
    TeamDto getByCoach(String coach);
    TeamDto getByChampionships(int champions);
    TeamDto getByFoundation(int year);

    //Personalizados Individual
    String getCoachByCountry(String country);
    String getFederationByCountry(String country);
    int getChampionshipsByCountry(String country);

    TeamDto updateTeam(TeamDto teamDto, int id);
    void deleteTeam(int id);
}
