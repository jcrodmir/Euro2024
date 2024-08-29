package com.Euro2024.service;

import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.TeamDto;
import com.Euro2024.models.PlayerEntity;
import com.Euro2024.models.TeamEntity;
import com.Euro2024.repository.PlayerRepository;
import com.Euro2024.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class playerServiceTests {
    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;


    private PlayerEntity player;
    private PlayerDto playerDto;
    private TeamEntity team;
    private TeamDto teamDto;

    @BeforeEach
    public void init() {


        team = TeamEntity.builder()
                .foundation(1989)
                .federation("Real Federación Española de Fútbol")
                .country("España")
                .coach("De la Fuente")
                .championships(4)
                .build();

        teamDto = TeamDto.builder()
                .foundation(1989)
                .federation("Real Federación Española de Fútbol")
                .country("España")
                .coach("De la Fuente")
                .championships(4)
                .build();
        player = PlayerEntity.builder()
                .name("Lionel Messi")
                .dorsal(10)
                .position(PlayerEntity.Position.Delantero)
                .goals(50)
                .yellowCard(5)
                .redCard(1)
                .assists(20)
                .goalkeeperSaves(0)
                .ballsRecovered(15)
                .minutesPlayed(3000)
                .matchesPlayed(30)
                .build();
        playerDto = PlayerDto.builder()
                .name("Lionel Messi")
                .dorsal(9)
                .position(PlayerEntity.Position.Delantero)
                .goals(50)
                .yellowCard(5)
                .redCard(1)
                .assists(20)
                .goalkeeperSaves(0)
                .ballsRecovered(15)
                .minutesPlayed(3000)
                .matchesPlayed(30)
                .build();
    }
    @Test
    public void PlayerService_CreatePlayer_ReturnPlayerDTO(){
        team.setId(1);
        player.setId(1);
        when(teamRepository.findById(team.getId())).thenReturn(Optional.of(team));
        when(playerRepository.save(Mockito.any(PlayerEntity.class))).thenReturn(player);

        PlayerDto savedPlayer = playerService.createPlayer(playerDto,team.getId());

        Assertions.assertThat(savedPlayer).isNotNull();


    }
    @Test
    public void PlayerService_getPlayerByName_ReturnPlayerDTO(){

        when(playerRepository.findByName(player.getName())).thenReturn(player);


        PlayerDto playerDto = playerService.getPlayerByName(player.getName());

        Assertions.assertThat(playerDto).isNotNull();


    }
    @Test
    public void PlayerService_getPlayerById_ReturnPlayerDTO(){
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));

        PlayerDto playerDto = playerService.getPlayerById(player.getId());

        Assertions.assertThat(playerDto).isNotNull();


    }
    @Test
    public void PlayerService_getAllPlayersFromTeam_ReturnListPlayerDTO(){
        List<PlayerEntity> players = List.of(player);
        team.setId(1);
        player.setId(1);
        when(teamRepository.findById(team.getId())).thenReturn(Optional.of(team));
        when(playerRepository.findByTeam(team)).thenReturn(players);


        List<PlayerDto> playersDto = playerService.getAllPlayersFromTeam(team.getId());

        Assertions.assertThat(playersDto).isNotNull();
        Assertions.assertThat(playersDto).hasSize(1);
        Assertions.assertThat(playersDto.get(0).getName()).isEqualTo("Lionel Messi");

    }

    @Test
    public void PlayerService_getPlayerNumberByDorsal_ReturnListPlayerDTO(){
        List<PlayerEntity> players = List.of(player);

        when(playerRepository.findByDorsal(player.getDorsal())).thenReturn(players);


        List<PlayerDto> playersDto = playerService.getPlayerNumberByDorsal(player.getDorsal());

        Assertions.assertThat(playersDto).isNotNull();
        Assertions.assertThat(playersDto).hasSize(1);
        Assertions.assertThat(playersDto.get(0).getDorsal()).isEqualTo(10);

    }
}
