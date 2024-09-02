package com.Euro2024.controller;

import com.Euro2024.dto.GenericResponse;
import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.TeamDto;
import com.Euro2024.models.PlayerEntity;
import com.Euro2024.models.TeamEntity;
import com.Euro2024.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bucket;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = PlayerController.class)
@AutoConfigureMockMvc(addFilters = false)

public class PlayerControllerTest {
    @Mock
    private Bucket bucket;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;
    @Autowired
    private ObjectMapper objectMapper;
    private PlayerEntity player;
    private PlayerDto playerDto;
    private TeamEntity team;
    private TeamDto teamDto;

    @BeforeEach
    public void init() {

        Mockito.reset(bucket, playerService);

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
        playerDto = PlayerDto.builder()
                .name("Cristiano")
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
    public void PlayerController_getPlayersById_ReturnPlayerDto() throws Exception {
        int playerId = 1;
        // Configuración del mock para el bucket y el servicio
        when(bucket.tryConsume(1)).thenReturn(true);

        when(playerService.getPlayerById(playerId)).thenReturn(playerDto);

        ResultActions response = mockMvc.perform(get("/api/player/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dorsal", CoreMatchers.is(9)));
    }
    @Test
    public void PlayerController_getPlayers_ReturnPlayerDto() throws Exception {
        when(bucket.tryConsume(1)).thenReturn(true);

        List<PlayerDto> listOfPlayer= new ArrayList<>();
        listOfPlayer.add(playerDto);
        listOfPlayer.add(playerDto);
        GenericResponse<PlayerDto> players = new GenericResponse<>();
        players.setContent(listOfPlayer);
        players.setPageNo(1);
        players.setPageSize(10);
        players.setTotalPages(1);
        players.setTotalElement(2);
        players.setLast(true);

        when(playerService.getAllPlayer(0,10)).thenReturn(players);

        ResultActions response = mockMvc.perform(get("/api/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].name").value("Cristiano"));;
    }
}
