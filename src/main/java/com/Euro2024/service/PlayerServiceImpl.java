package com.Euro2024.service;

import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.GenericResponse;
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
public class PlayerServiceImpl implements PlayerService{

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto,int teamId) {
        PlayerEntity playerEntity = getPlayerEntity(playerDto);
        TeamEntity team=  teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("No se encontro el equipo"));
        playerEntity.setTeam(team);

        PlayerEntity newPlayer= playerRepository.save(playerEntity);

        return getPlayerDto(newPlayer);
    }


    @Override
    public GenericResponse<PlayerDto> getAllPlayer(int page, int pageSize) {

        Pageable pageable= PageRequest.of(page-1,pageSize);
        Page<PlayerEntity> playerPage = playerRepository.findAll(pageable);

        List<PlayerDto> content = playerPage.stream().map(this::getPlayerDto).collect(Collectors.toList());

        GenericResponse<PlayerDto> playerResponse=new GenericResponse<>();
        playerResponse.setContent(content);
        playerResponse.setPageNo(playerPage.getNumber()+1);
        playerResponse.setPageSize(playerPage.getSize());
        playerResponse.setTotalPages(playerPage.getTotalPages());
        playerResponse.setTotalElement(playerPage.getTotalElements());
        playerResponse.setLast(playerPage.isLast());

        return playerResponse;
    }

    @Override
    public List<PlayerDto> getAllPlayersFromTeam(int teamId) {
        TeamEntity team=  teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("No se encontro el equipo"));
        List<PlayerEntity> listOfPlayer= playerRepository.findByTeam(team);
        return listOfPlayer.stream().map(this::getPlayerDto).collect(Collectors.toList());
    }

    @Override
    public List<PlayerDto> getAllPlayerFromTeam(int teamId, int id) {
        return List.of();
    }

    @Override
    public PlayerDto getPlayerById(int id) {
        PlayerEntity player= playerRepository.findById(id).orElseThrow();

        return getPlayerDto(player);
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto ,int id) {
        PlayerEntity player= playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + id));//.orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        player.setName(playerDto.getName());
        player.setGoals(playerDto.getGoals());
        player.setAssists(playerDto.getAssists());
        player.setDorsal(playerDto.getDorsal());
        player.setPosition(playerDto.getPosition());
        player.setBallsRecovered(playerDto.getBallsRecovered());
        player.setRedCard(playerDto.getRedCard());
        player.setYellowCard(playerDto.getYellowCard());
        player.setGoalkeeperSaves(playerDto.getGoalkeeperSaves());
        player.setMinutesPlayed(playerDto.getMinutesPlayed());
        player.setMatchesPlayed(playerDto.getMatchesPlayed());


        PlayerEntity updatedPlayer= playerRepository.save(player);

        return getPlayerDto(updatedPlayer);
    }

    @Override
    public void deletePlayer(int id) {
        PlayerEntity player= playerRepository.findById(id).orElseThrow();//.orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));

        playerRepository.delete(player);
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
