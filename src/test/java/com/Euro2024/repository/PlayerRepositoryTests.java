package com.Euro2024.repository;

import com.Euro2024.models.PlayerEntity;
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
public class PlayerRepositoryTests {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void PlayerRepository_Saved_ReturnPlayer() {
        PlayerEntity player = PlayerEntity.builder()
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

        // Guardar el jugador en la base de datos
        PlayerEntity savedPlayer = playerRepository.save(player);

        // Verificar que el jugador tiene un ID generado
        assertNotNull(savedPlayer.getId());

        // Recuperar el jugador por ID
        PlayerEntity retrievedPlayer = playerRepository.findById(savedPlayer.getId()).orElse(null);

        // Verificar que el jugador recuperado no es nulo y que sus datos son correctos
        assertNotNull(retrievedPlayer);
        assertEquals("Lionel Messi", retrievedPlayer.getName());
        assertEquals(10, retrievedPlayer.getDorsal());
        assertEquals(PlayerEntity.Position.Delantero, retrievedPlayer.getPosition());
        assertEquals(50, retrievedPlayer.getGoals());
        assertEquals(5, retrievedPlayer.getYellowCard());
        assertEquals(1, retrievedPlayer.getRedCard());
        assertEquals(20, retrievedPlayer.getAssists());
        assertEquals(0, retrievedPlayer.getGoalkeeperSaves());
        assertEquals(15, retrievedPlayer.getBallsRecovered());
        assertEquals(3000, retrievedPlayer.getMinutesPlayed());
        assertEquals(30, retrievedPlayer.getMatchesPlayed());
    }

    @Test
    void PlayerRepository_FindById_ReturnPlayer() {
        PlayerEntity player = PlayerEntity.builder()
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
        playerRepository.save(player);

        PlayerEntity playerRecovered = playerRepository.findById(player.getId()).get();

        Assertions.assertThat(playerRecovered).isNotNull();
    }

    @Test
    public void PlayerRepository_GetAll_ReturnMoreThenOnePlaver() {
        PlayerEntity player = PlayerEntity.builder()
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
        PlayerEntity player2 = PlayerEntity.builder()
                .name("Cristiano")
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

        playerRepository.save(player);
        playerRepository.save(player2);

        List<PlayerEntity> playerList = playerRepository.findAll();

        Assertions.assertThat(playerList).isNotNull();
        Assertions.assertThat(playerList.size()).isEqualTo(2);

    }

    @Test
    public void PlayerRepository_UpdatePlayer_ReturnPlayerNotNull() {
        PlayerEntity player = PlayerEntity.builder()
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
        playerRepository.save(player);

        //Player was saving and setName, resaving name
        PlayerEntity playerSave= playerRepository.findById(player.getId()).get();
        playerSave.setName("Cristiano");
        PlayerEntity playerUpdated=playerRepository.save(playerSave);

        //UPDATE WAS REALIZING
        Assertions.assertThat(playerUpdated.getName()).isNotNull();
        Assertions.assertThat(playerUpdated.getName()).isEqualTo("Cristiano");

        //cHECK THAT IN DATABASE THERE IS ONLY ONE PLAYER
        List<PlayerEntity> playerList = playerRepository.findAll();
        Assertions.assertThat(playerList).isNotNull();
        Assertions.assertThat(playerList.size()).isEqualTo(1);

    }

    @Test
    public void PlayerRepository_PlayerDelete_ReturnPlayerIsEmpty() {
        PlayerEntity player = PlayerEntity.builder()
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
        playerRepository.save(player);

        playerRepository.deleteById(player.getId());
        //Al poder retornar null no usamos el get si no que buscamos el optional
        Optional<PlayerEntity> playerReturn = playerRepository.findById(player.getId());

        Assertions.assertThat(playerReturn).isEmpty();
    }
}
