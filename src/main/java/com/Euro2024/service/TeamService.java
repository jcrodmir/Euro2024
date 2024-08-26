package com.Euro2024.service;

import com.Euro2024.dto.GenericResponse;
import com.Euro2024.dto.TeamDto;

public interface TeamService {
    TeamDto createTeam(TeamDto teamDto);
    //parameters is for the pagination
    GenericResponse<TeamDto> getAllTeam(int page, int pageSize);
    TeamDto getTeamById(int id);
    TeamDto updateTeam(TeamDto teamDto, int id);
    void deleteTeam(int id);
}
