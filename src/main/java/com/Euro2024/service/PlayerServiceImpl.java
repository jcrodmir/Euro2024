package com.Euro2024.service;

import com.Euro2024.dto.PlayerDto;
import com.Euro2024.models.PlayerEntity;
import com.Euro2024.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService{

    private PlayerRepository playerRepository;
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        PlayerEntity playerEntity = getPlayerEntity(playerDto);

        PlayerEntity newPlayer= playerRepository.save(playerEntity);

        PlayerDto playerResponse=getPlayerDto(newPlayer);

        return playerResponse;
    }


    @Override
    public List<PlayerDto> getAllPlayer(int page, int pageSize) {

        List<PlayerEntity> playerList= playerRepository.findAll();
        List<PlayerDto> content = playerList.stream().map(this::getPlayerDto).collect(Collectors.toList());

        return content;
    }

    @Override
    public PlayerDto getPlayerById(int id) {
        PlayerEntity player= playerRepository.findById(id).orElseThrow();

        return getPlayerDto(player);
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto, int id) {
        PlayerEntity player= playerRepository.findById(id).orElseThrow();//.orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        player=getPlayerEntity(playerDto);
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


        return playerResponse;
    }
}
