package com.Euro2024.repository;


import com.Euro2024.models.TeamEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class TeamRepositoryTests {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void TeamRepository_Saved_ReturnTeam(){
        TeamEntity player = TeamEntity.builder()
                .foundation(1989)
                .federation("Real Federación Española de Fútbol")
                .country("España")
                .coach("De la Fuente")
                .championships(4)
                .build();

        // Guardar el jugador en la base de datos
        TeamEntity savedTeam = teamRepository.save(player);

        // Verificar que el jugador tiene un ID generado
        assertNotNull(savedTeam.getId());

        // Recuperar el jugador por ID
        TeamEntity recoveredTeam = teamRepository.findById(savedTeam.getId()).orElse(null);

        // Verificar que el jugador recuperado no es nulo y que sus datos son correctos
        assertNotNull(recoveredTeam);
        assertEquals(1989, recoveredTeam.getFoundation());
        assertEquals(4, recoveredTeam.getChampionships());
        assertEquals("De la Fuente", recoveredTeam.getCoach());
        assertEquals("Real Federación Española de Fútbol", recoveredTeam.getFederation());
        assertEquals("España", recoveredTeam.getCountry());
    }


    @Test
    void TeamRepository_FindById_ReturnTeam() {
        TeamEntity team = TeamEntity.builder()
                .foundation(1989)
                .federation("Real Federación Española de Fútbol")
                .country("España")
                .coach("De la Fuente")
                .championships(4)
                .build();
        teamRepository.save(team);

        TeamEntity teamRecovered = teamRepository.findById(team.getId()).get();

        Assertions.assertThat(teamRecovered).isNotNull();
    }

    @Test
    public void TeamRepository_GetAll_ReturnMoreThenOneTeam() {
        TeamEntity team = TeamEntity.builder()
                .foundation(1989)
                .federation("Real Federación Española de Fútbol")
                .country("España")
                .coach("De la Fuente")
                .championships(4)
                .build();
        TeamEntity team2 = TeamEntity.builder()
                .foundation(1989)
                .federation("Deutsche Fussball Federation")
                .country("Alemania")
                .coach("Nagelsmann")
                .championships(4)
                .build();

        teamRepository.save(team);
        teamRepository.save(team2);

        List<TeamEntity> teamList = teamRepository.findAll();

        Assertions.assertThat(teamList).isNotNull();
        Assertions.assertThat(teamList.size()).isEqualTo(2);

    }

    @Test
    public void PlayerRepository_UpdatePlayer_ReturnPlayerNotNull() {
        TeamEntity team = TeamEntity.builder()
                .foundation(1989)
                .federation("Real Federación Española de Fútbol")
                .country("España")
                .coach("De la Fuente")
                .championships(4)
                .build();
        teamRepository.save(team);

        //Player was saving and setName, resaving name
        TeamEntity teamSave= teamRepository.findById(team.getId()).get();
        teamSave.setFoundation(1902);
        TeamEntity teamUpdated=teamRepository.save(teamSave);

        //UPDATE WAS REALIZING
        Assertions.assertThat(teamUpdated.getFoundation()).isNotZero();
        Assertions.assertThat(teamUpdated.getFoundation()).isEqualTo(1902);

        //cHECK THAT IN DATABASE THERE IS ONLY ONE PLAYER
        List<TeamEntity> teamList = teamRepository.findAll();
        Assertions.assertThat(teamList).isNotNull();
        Assertions.assertThat(teamList.size()).isEqualTo(1);

    }

    @Test
    public void PlayerRepository_PlayerDelete_ReturnPlayerIsEmpty() {
        TeamEntity team = TeamEntity.builder()
                .foundation(1989)
                .federation("Real Federación Española de Fútbol")
                .country("España")
                .coach("De la Fuente")
                .championships(4)
                .build();
        teamRepository.save(team);

        teamRepository.deleteById(team.getId());
        //Al poder retornar null no usamos el get si no que buscamos el optional
        Optional<TeamEntity> teamReturn = teamRepository.findById(team.getId());

        Assertions.assertThat(teamReturn).isEmpty();
    }

}
