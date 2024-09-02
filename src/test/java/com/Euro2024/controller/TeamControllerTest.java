package com.Euro2024.controller;

import com.Euro2024.dto.GenericResponse;
import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.TeamDto;
import com.Euro2024.models.PlayerEntity;
import com.Euro2024.models.TeamEntity;
import com.Euro2024.service.PlayerService;
import com.Euro2024.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bucket;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = TeamController.class)
@AutoConfigureMockMvc(addFilters = false)

public class TeamControllerTest {
    @Mock
    private Bucket bucket;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeamService teamService;
    @Autowired
    private ObjectMapper objectMapper;
    private PlayerEntity player;
    private PlayerDto playerDto;
    private TeamEntity team;
    private TeamDto teamDto;

    @BeforeEach
    public void init() {

        Mockito.reset(bucket, teamService);

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
    public void TeamController_createTeam_ReturnTeamDto() throws Exception {

        // Configuración del mock para el bucket y el servicio
        when(bucket.tryConsume(1)).thenReturn(true);

        when(teamService.createTeam(teamDto)).thenReturn(teamDto);

        ResultActions response = mockMvc.perform(post("/api/team/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is("España")));
    }
    @Test
    public void TeamController_updateTeam_ReturnTeamDto() throws Exception {
        when(bucket.tryConsume(1)).thenReturn(true);

        when(teamService.updateTeam(teamDto,1)).thenReturn(teamDto);

        ResultActions response = mockMvc.perform(put("/api/team/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is("España")));
    }
    @Test
    public void TeamController_deleteTeam_ReturnTeamDto() throws Exception {
        when(bucket.tryConsume(1)).thenReturn(true);


        doNothing().when(teamService).deleteTeam(1);

        ResultActions response = mockMvc.perform(delete("/api/team/1/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString("Team was deleting")));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

