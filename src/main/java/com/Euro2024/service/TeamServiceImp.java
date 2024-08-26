package com.Euro2024.service;

import com.Euro2024.dto.GenericResponse;
import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.TeamDto;
import com.Euro2024.models.PlayerEntity;
import com.Euro2024.models.TeamEntity;
import com.Euro2024.repository.PlayerRepository;
import com.Euro2024.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImp implements TeamService {

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    @Autowired
    public TeamServiceImp(PlayerRepository playerRepository,TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        TeamEntity teamEntity = getTeamEntity(teamDto);

        TeamEntity newTeam= teamRepository.save(teamEntity);

        return getTeamDto(newTeam);
    }

    @Override
    public GenericResponse<TeamDto> getAllTeam(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page-1,pageSize);
        Page<TeamEntity> teamPage = teamRepository.findAll(pageable);

        List<TeamDto> content = teamPage.stream().map(this::getTeamDto).collect(Collectors.toList());

        GenericResponse<TeamDto> teamResponse=new GenericResponse<>();
        teamResponse.setContent(content);
        teamResponse.setPageNo(teamPage.getNumber()+1);
        teamResponse.setPageSize(teamPage.getSize());
        teamResponse.setTotalPages(teamPage.getTotalPages());
        teamResponse.setTotalElement(teamPage.getTotalElements());
        teamResponse.setLast(teamPage.isLast());

        return teamResponse;
    }

    @Override
    public TeamDto getTeamById(int id) {
        TeamEntity player= teamRepository.findById(id).orElseThrow();

        return getTeamDto(player);
    }

    @Override
    public TeamDto updateTeam(TeamDto teamDto, int id) {
        TeamEntity team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + id));

        team.setFoundation(teamDto.getFoundation());
        team.setCoach(teamDto.getCoach());
        team.setChampionships(teamDto.getChampionships());
        team.setCountry(teamDto.getCountry());
        team.setFederation(teamDto.getFederation());


        TeamEntity updatedTeam= teamRepository.save(team);


        return getTeamDto(updatedTeam);
    }

    @Override
    public void deleteTeam(int id) {
        TeamEntity team= teamRepository.findById(id).orElseThrow();//.orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));

        teamRepository.delete(team);
    }

    private TeamEntity getTeamEntity(TeamDto teamDto) {
        TeamEntity teamEntity=new TeamEntity();

        teamEntity.setId(teamDto.getId());
        teamEntity.setFoundation(teamDto.getFoundation());
        teamEntity.setCoach(teamDto.getCoach());
        teamEntity.setChampionships(teamDto.getChampionships());
        teamEntity.setCountry(teamDto.getCountry());
        teamEntity.setFederation(teamDto.getFederation());
        if (teamDto.getPlayers() != null) {
            List<PlayerEntity> players = teamDto.getPlayers().stream()
                    .map(this::getPlayerEntity)  // Convertir cada PlayerDto a PlayerEntity
                    .collect(Collectors.toList());

            teamEntity.setPlayers(players);
        }
        return teamEntity;
    }

    private TeamDto getTeamDto(TeamEntity teamEntity) {
        TeamDto teamDto=new TeamDto();

        teamDto.setId(teamEntity.getId());
        teamDto.setChampionships(teamEntity.getChampionships());
        teamDto.setCoach(teamEntity.getCoach());
        teamDto.setCountry(teamEntity.getCountry());
        teamDto.setFederation(teamEntity.getFederation());
        teamDto.setFoundation(teamEntity.getFoundation());
        if (teamEntity.getPlayers() != null) {
            List<PlayerDto> players = teamEntity.getPlayers().stream()
                    .map(this::getPlayerDto)  // Convertir cada PlayerEntity a PlayerDto
                    .collect(Collectors.toList());

            teamDto.setPlayers(players);
        }


        return teamDto;
    }
    private PlayerEntity getPlayerEntity(PlayerDto playerDto) {
        PlayerEntity playerEntity=new PlayerEntity();

        playerEntity.setName(playerDto.getName());
        playerEntity.setGoals(playerDto.getGoals());
        playerEntity.setAssists(playerDto.getAssists());
        playerEntity.setDorsal(playerDto.getDorsal());
        playerEntity.setPosition(playerDto.getPosition());
        playerEntity.setBallsRecovered(playerDto.getBallsRecovered());
        playerEntity.setRedCard(playerDto.getRedCard());
        playerEntity.setYellowCard(playerDto.getYellowCard());
        playerEntity.setGoalkeeperSaves(playerDto.getGoalkeeperSaves());
        playerEntity.setMinutesPlayed(playerDto.getMinutesPlayed());
        playerEntity.setMatchesPlayed(playerDto.getMatchesPlayed());
        return playerEntity;
    }

    private PlayerDto getPlayerDto(PlayerEntity playerEntity) {
        PlayerDto playerResponse=new PlayerDto();

        playerResponse.setId(playerEntity.getId());
        playerResponse.setName(playerEntity.getName());
        playerResponse.setGoals(playerEntity.getGoals());
        playerResponse.setAssists(playerEntity.getAssists());
        playerResponse.setDorsal(playerEntity.getDorsal());
        playerResponse.setPosition(playerEntity.getPosition());
        playerResponse.setBallsRecovered(playerEntity.getBallsRecovered());
        playerResponse.setRedCard(playerEntity.getRedCard());
        playerResponse.setYellowCard(playerEntity.getYellowCard());
        playerResponse.setGoalkeeperSaves(playerEntity.getGoalkeeperSaves());
        playerResponse.setMinutesPlayed(playerEntity.getMinutesPlayed());
        playerResponse.setMatchesPlayed(playerEntity.getMatchesPlayed());
        // Solo incluir el ID del equipo, no la entidad completa
        if (playerEntity.getTeam() != null) {
            playerResponse.setTeamId(playerEntity.getTeam().getId());
        }


        return playerResponse;
    }
}
