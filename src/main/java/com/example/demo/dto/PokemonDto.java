package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PokemonDto {
    private Long id;
    private String name;
    private Integer height;
    private Integer weight;
    private Integer baseExperience;
    private String imageUrl;
    private List<String> types;
    private List<String> abilities;
    private List<StatDto> stats;
}
