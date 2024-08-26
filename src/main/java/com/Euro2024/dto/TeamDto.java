package com.Euro2024.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private int id;
    private String country;
    private String federation;
    private String coach;
    private int championships;
    private int foundation;
    private List<PlayerDto> players;
}
