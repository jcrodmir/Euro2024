package com.Euro2024.service;

import com.Euro2024.dto.GenericResponse;
import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.TeamDto;
import com.Euro2024.models.PlayerEntity;
import com.Euro2024.models.TeamEntity;
import com.Euro2024.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class teamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImp teamService;

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
    }

    @Test
    public void TeamService_CreateTeam_ReturnTeamDTO(){

        when(teamRepository.save(Mockito.any(TeamEntity.class))).thenReturn(team);

        TeamDto teamDto1=teamService.createTeam(teamDto);

        Assertions.assertThat(teamDto1).isNotNull();
    }

    @Test
    public void TeamService_getTeamById_ReturnTeamDTO(){
        team.setId(1);
        when(teamRepository.findById(team.getId())).thenReturn(Optional.of(team));

        TeamDto teamDto1=teamService.getTeamById(1);

        Assertions.assertThat(teamDto1).isNotNull();
    }

    @Test
    public void TeamService_getAllTeam_ReturnGenericResponseOfTeamDTO(){
        List<TeamEntity> listOfTeams= new ArrayList<>();
        listOfTeams.add(team);
        listOfTeams.add(team);
        Page<TeamEntity> teamPage = new PageImpl<>(listOfTeams);
        Pageable pageable = PageRequest.of(0, 1);

        when(teamRepository.findAll(pageable)).thenReturn(teamPage);

        GenericResponse<TeamDto> teamResponse=new GenericResponse<>();
        teamResponse=teamService.getAllTeam(1,1);

        Assertions.assertThat(teamResponse).isNotNull();
        Assertions.assertThat(teamResponse.getContent()).hasSize(2);
        Assertions.assertThat(teamResponse.getContent().get(1).getChampionships()).isEqualTo(4);

    }
    @Test
    public void TeamService_getByCountry_ReturnTeamDTO(){


        when(teamRepository.findByCountry(team.getCountry())).thenReturn(team);


        TeamDto teamDto1 = teamService.getByCountry(teamDto.getCountry());

        Assertions.assertThat(teamDto1).isNotNull();
        Assertions.assertThat(teamDto1.getCountry()).isEqualTo("España");

    }


}
