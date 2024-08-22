package com.Euro2024.dto;
import com.Euro2024.models.PlayerEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private int id;
    private String name;
    private int dorsal;
    @Enumerated(EnumType.STRING)
    private PlayerEntity.Position position;
    private int goals;
    private int yellowCard;
    private int redCard;
    private int assists;
    private int goalkeeperSaves;
    private int ballsRecovered;
    private int minutesPlayed;
    private int MatchesPlayed;
}
