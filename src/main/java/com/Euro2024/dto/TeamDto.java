package com.Euro2024.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    public TeamDto(String coach) {
        this.coach = coach;
    }

    private int id;
    private String country;
    private String federation;
    private String coach;
    private int championships;
    private int foundation;
    private List<PlayerDto> players;
}
