package com.Euro2024.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PlayerEntity {
    //Create a Enum for the player position
    public enum  Position{
        Portero,
        Defensa,
        Mediocampista,
        Delantero

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int dorsal;
    private Position position;
    private int goals;
    private int yellowCard;
    private int redCard;
    private int assists;
    private int goalkeeperSaves;
    private int ballsRecovered;
    private int minutesPlayed;
    private int MatchesPlayed;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private TeamEntity team;

}
